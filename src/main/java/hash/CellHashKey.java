package hash;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;

public abstract class CellHashKey extends HashKey<Cell> {
  protected static final byte[] LATEST_TS = Bytes.toBytes(HConstants.LATEST_TIMESTAMP);
  protected static final byte MAX_TYPE = KeyValue.Type.Maximum.getCode();

  public CellHashKey(Cell cell) {
    super(cell);
  }
}
