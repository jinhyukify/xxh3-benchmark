package hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.hadoop.hbase.unsafe.HBasePlatformDependent;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.io.netty.util.internal.PlatformDependent;

public class UnsafeAccess {

  /** The offset to the first element in a byte array. */
  public static final long BYTE_ARRAY_BASE_OFFSET;

  public static final boolean LITTLE_ENDIAN =
    ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);

  // This number limits the number of bytes to copy per call to Unsafe's
  // copyMemory method. A limit is imposed to allow for safepoint polling
  // during a large copy
  static final long UNSAFE_COPY_THRESHOLD = 1024L * 1024L;
  static {
    if (HBasePlatformDependent.isUnsafeAvailable()) {
      BYTE_ARRAY_BASE_OFFSET = HBasePlatformDependent.arrayBaseOffset(byte[].class);
    } else {
      BYTE_ARRAY_BASE_OFFSET = -1;
    }
  }

  private UnsafeAccess() {
  }

  /**
   * Converts a byte array to an int value considering it was written in little-endian format.
   * @param bytes  byte array
   * @param offset offset into array
   * @return the int value
   */
  public static int toIntLE(byte[] bytes, int offset) {
    if (!LITTLE_ENDIAN) {
      return Integer
        .reverseBytes(HBasePlatformDependent.getInt(bytes, offset + BYTE_ARRAY_BASE_OFFSET));
    } else {
      return HBasePlatformDependent.getInt(bytes, offset + BYTE_ARRAY_BASE_OFFSET);
    }
  }

  /**
   * Converts a byte array to a long value considering it was written in little-endian format.
   * @param bytes  byte array
   * @param offset offset into array
   * @return the long value
   */
  public static long toLongLE(byte[] bytes, int offset) {
    if (!LITTLE_ENDIAN) {
      return Long
        .reverseBytes(HBasePlatformDependent.getLong(bytes, offset + BYTE_ARRAY_BASE_OFFSET));
    } else {
      return HBasePlatformDependent.getLong(bytes, offset + BYTE_ARRAY_BASE_OFFSET);
    }
  }

  /**
   * Put an int value out to the specified byte array position in little-endian format.
   * @param bytes  the byte array
   * @param offset position in the array
   * @param val    int to write out
   * @return incremented offset
   */
  public static int putIntLE(byte[] bytes, int offset, int val) {
    if (!LITTLE_ENDIAN) {
      val = Integer.reverseBytes(val);
    }
    HBasePlatformDependent.putInt(bytes, offset + BYTE_ARRAY_BASE_OFFSET, val);
    return offset + Bytes.SIZEOF_INT;
  }

  /**
   * Put a long value out to the specified byte array position in little-endian format.
   * @param bytes  the byte array
   * @param offset position in the array
   * @param val    long to write out
   * @return incremented offset
   */
  public static int putLongLE(byte[] bytes, int offset, long val) {
    if (!LITTLE_ENDIAN) {
      val = Long.reverseBytes(val);
    }
    HBasePlatformDependent.putLong(bytes, offset + BYTE_ARRAY_BASE_OFFSET, val);
    return offset + Bytes.SIZEOF_LONG;
  }

  /**
   * Reads an int value at the given buffer's offset considering it was written in little-endian
   * format.
   * @return int value at offset
   */
  public static int toIntLE(ByteBuffer buf, int offset) {
    if (!LITTLE_ENDIAN) {
      return Integer.reverseBytes(getAsInt(buf, offset));
    }
    return getAsInt(buf, offset);
  }

  /**
   * Reads bytes at the given offset as an int value.
   * @return int value at offset
   */
  private static int getAsInt(ByteBuffer buf, int offset) {
    if (buf.isDirect()) {
      return HBasePlatformDependent.getInt(directBufferAddress(buf) + offset);
    }
    return HBasePlatformDependent.getInt(buf.array(),
      BYTE_ARRAY_BASE_OFFSET + buf.arrayOffset() + offset);
  }

  public static long toLongLE(ByteBuffer buf, int offset) {
    if (!LITTLE_ENDIAN) {
      return Long.reverseBytes(getAsLong(buf, offset));
    }
    return getAsLong(buf, offset);
  }

  /**
   * Reads bytes at the given offset as a long value.
   * @return long value at offset
   */
  private static long getAsLong(ByteBuffer buf, int offset) {
    if (buf.isDirect()) {
      return HBasePlatformDependent.getLong(directBufferAddress(buf) + offset);
    }
    return HBasePlatformDependent.getLong(buf.array(),
      BYTE_ARRAY_BASE_OFFSET + buf.arrayOffset() + offset);
  }

  public static long directBufferAddress(ByteBuffer buf) {
    return PlatformDependent.directBufferAddress(buf);
  }
}
