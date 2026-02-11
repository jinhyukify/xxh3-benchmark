package hash;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.PrivateCellUtil;
import org.apache.hadoop.hbase.util.Bytes;

public class RowColBloomHashKey extends CellHashKey {
  private final int rowLength;
  private final int qualLength;
  private final int totalLength;

  public RowColBloomHashKey(Cell cell) {
    super(cell);
    rowLength = cell.getRowLength();
    // We don't consider the family length for ROWCOL bloom. So subtract the famLen from the
    // length calculation. Timestamp and type are of no relevance here
    qualLength = cell.getQualifierLength();
    // For ROW_COL blooms we use bytes
    // <RK length> (2 bytes) , <RK>, 0 (one byte CF length), <CQ>, <TS> (8 btes), <TYPE> ( 1 byte)
    totalLength = KeyValue.ROW_LENGTH_SIZE + rowLength + KeyValue.FAMILY_LENGTH_SIZE
      + qualLength + KeyValue.TIMESTAMP_TYPE_SIZE;
  }

  @Override
  public byte get(int offset) {
    // For ROW_COL blooms we use bytes
    // <RK length> (2 bytes) , <RK>, 0 (one byte CF length), <CQ>, <TS> (8 btes), <TYPE> ( 1 byte)
    if (offset < Bytes.SIZEOF_SHORT) {
      // assign locally
      int rowlen = rowLength;
      byte b = (byte) rowlen;
      if (offset == 0) {
        rowlen >>= 8;
        b = (byte) rowlen;
      }
      return b;
    }
    int refLen = Bytes.SIZEOF_SHORT + rowLength;
    if (offset < refLen) {
      return PrivateCellUtil.getRowByte(t, offset - Bytes.SIZEOF_SHORT);
    }
    if (offset == refLen) {
      // The fam length should return 0 assuming there is no column family.
      // Because for ROWCOL blooms family is not considered
      return 0;
    }
    refLen += qualLength + Bytes.SIZEOF_BYTE;
    // skip the family len because actual cells may have family also
    if (offset < refLen) {
      return PrivateCellUtil.getQualifierByte(t,
        offset - (Bytes.SIZEOF_SHORT + rowLength + Bytes.SIZEOF_BYTE));
    }
    // TODO : check if ts and type can be removed
    refLen += KeyValue.TIMESTAMP_SIZE;
    if (offset < refLen) {
      return LATEST_TS[offset - (Bytes.SIZEOF_SHORT + rowLength + qualLength + Bytes.SIZEOF_BYTE)];
    }
    return MAX_TYPE;
  }

  @Override
  public int length() {
      return totalLength;
  }

  @Override
  public int getIntLE(int offset) {
    // Handle fast path that can return the row key as int directly
    final int rowEnd = KeyValue.ROW_LENGTH_SIZE + rowLength;
    if (offset >= KeyValue.ROW_LENGTH_SIZE && offset + Bytes.SIZEOF_INT <= rowEnd) {
      return LittleEndianBytes.getRowAsIntLE(t, offset - KeyValue.ROW_LENGTH_SIZE);
    }

    final int qualStart = rowEnd + KeyValue.FAMILY_LENGTH_SIZE; // 9
    final int qualEnd = qualStart + qualLength; //9 + 6 = 15
    if (offset >= qualStart && offset + Bytes.SIZEOF_INT <= qualEnd) {
      return LittleEndianBytes.getQualifierAsIntLE(t, offset - qualStart);
    }

    final int tsEnd = qualEnd + KeyValue.TIMESTAMP_SIZE; // 15 + 8 = 23
    if (offset >= qualEnd && offset + Bytes.SIZEOF_INT <= tsEnd) {
      return LittleEndianBytes.toIntLE(LATEST_TS, offset - qualEnd);
    }

    return (int) assembleCrossingLE(offset, Bytes.SIZEOF_INT);
  }

  @Override
  public long getLongLE(int offset) {
    // Handle fast path that can return the row key as long directly
    final int rowEnd = KeyValue.ROW_LENGTH_SIZE + rowLength;
    if (offset >= KeyValue.ROW_LENGTH_SIZE && offset + Bytes.SIZEOF_LONG <= rowEnd) {
      return LittleEndianBytes.getRowAsLongLE(t, offset - KeyValue.ROW_LENGTH_SIZE);
    }

    final int qualStart = rowEnd + KeyValue.FAMILY_LENGTH_SIZE;
    final int qualEnd = qualStart + qualLength;
    if (offset >= qualStart && offset + Bytes.SIZEOF_LONG <= qualEnd) {
      return LittleEndianBytes.getQualifierAsLongLE(t, offset - qualStart);
    }

    if (offset == qualEnd) {
      return LittleEndianBytes.toLongLE(LATEST_TS, 0);
    }

    if (offset + Bytes.SIZEOF_LONG == totalLength) {
        return -1L;
    }

    return assembleCrossingLE(offset, Bytes.SIZEOF_LONG);
  }

  private long assembleCrossingLE(int offset, int wordBytes) {
    final int rowEnd = KeyValue.ROW_LENGTH_SIZE + rowLength;
    final int qualStart = rowEnd + KeyValue.FAMILY_LENGTH_SIZE;
    final int qualEnd = qualStart + qualLength;
    final int tsEnd = qualEnd + KeyValue.TIMESTAMP_SIZE;

    long result = 0L;
    int pos = offset;
    int remaining = wordBytes;

    while (remaining > 0) {
      // 1) row length field [0,2)
      if (pos < KeyValue.ROW_LENGTH_SIZE) {
        if (pos == 0) {
          result |= (rowLength >>> 8) & 0xFF;
          result |= (rowLength & 0xFF) << 8;
          pos += 2;
          remaining -= 2;
        } else {
          result |= rowLength & 0xFF;
          pos += 1;
          remaining -= 1;
        }
        continue;
      }

      // 2) row bytes [2, rowEnd)
      if (pos < rowEnd) {
        final int take = Math.min(rowEnd - pos, remaining);
        final int rOffset = pos - KeyValue.ROW_LENGTH_SIZE;
        for (int i = 0; i < take; i++) {
          final int shift = (wordBytes - remaining) * 8;
          final byte b = PrivateCellUtil.getRowByte(t, rOffset + i);
          result |= ((long) b & 0xFF) << shift;
          remaining -= 1;
        }
        pos += take;
        continue;
      }

      // 3) family length byte (always 0)
      if (pos == rowEnd) {
        pos += 1;
        remaining -= 1;
        continue;
      }

      // 4) qualifier bytes [qualStart, qualEnd)
      if (pos < qualEnd) {
        final int take = Math.min(qualEnd - pos, remaining);
        final int qOffset = pos - qualStart;
        for (int i = 0; i < take; i++) {
          final int shift = (wordBytes - remaining) * 8;
          final int b = PrivateCellUtil.getQualifierByte(t, qOffset + i) & 0xFF;
          result |= ((long) b) << shift;
          remaining -= 1;
        }
        pos += take;
        continue;
      }

      // 5) timestamp bytes [qualEnd, tsEnd) -> LATEST_TS
      if (pos < tsEnd) {
        final int take = Math.min(tsEnd - pos, remaining);
        final int tsOff = pos - qualEnd;
        for (int i = 0; i < take; i++) {
          final int shift = (wordBytes - remaining) * 8;
          final int b = LATEST_TS[tsOff + i] & 0xFF;
          result |= ((long) b) << shift;
          remaining -= 1;
        }
        pos += take;
        continue;
      }

      // 6) type byte at typePos -> MAX_TYPE
      final int shift = (wordBytes - remaining) * 8;
      result |= ((long) MAX_TYPE & 0xFF) << shift;
      pos += 1;
      remaining -= 1;
    }

    return result;
  }

  private long getRowPartialAsLE(int rowOffset, int length) {
    // 0 1 2 3 4 5 6 7 8 9
    assert length > 0 && length <= Bytes.SIZEOF_LONG;
    if (rowOffset + Bytes.SIZEOF_LONG <= rowLength) {
      return LittleEndianBytes.getRowAsLongLE(t, rowOffset);
    }

    long result = 0L;
    int remaining = length;
    if (rowOffset + Bytes.SIZEOF_INT <= rowLength) {
      result |= Integer.toUnsignedLong(LittleEndianBytes.getRowAsIntLE(t, rowOffset));
      remaining -= Bytes.SIZEOF_INT;
      rowOffset += Bytes.SIZEOF_INT;
    }

    for (int i = 0; i < remaining; i++) {
      byte b = PrivateCellUtil.getRowByte(t, rowOffset + (remaining - 1 - i));
      result <<= 8;
      result |= (b & 0xFFL);
    }
    return 0L;
  }
}
