package hash;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.PrivateCellUtil;

public class RowBloomHashKey extends CellHashKey {
    public RowBloomHashKey(Cell cell) {
        super(cell);
    }

    @Override
    public byte get(int offset) {
        return PrivateCellUtil.getRowByte(t, offset);
    }

    @Override
    public int length() {
        return this.t.getRowLength();
    }

    @Override
    public int getIntLE(int offset) {
        return LittleEndianBytes.getRowAsIntLE(t, offset);
    }

    @Override
    public long getLongLE(int offset) {
        return LittleEndianBytes.getRowAsLongLE(t, offset);
    }

}
