package hash;

import java.nio.ByteBuffer;

import org.apache.hadoop.hbase.ByteBufferExtendedCell;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.unsafe.HBasePlatformDependent;
import org.apache.hadoop.hbase.util.Bytes;

public class LittleEndianBytes {
    final static boolean UNSAFE_UNALIGNED = HBasePlatformDependent.unaligned();

    static abstract class Converter {
        abstract int toIntLE(byte[] bytes, int offset);

        abstract int toIntLE(ByteBuffer buffer, int offset);

        abstract int putIntLE(byte[] bytes, int offset, int val);

        abstract long toLongLE(byte[] bytes, int offset);

        abstract long toLongLE(ByteBuffer buffer, int offset);

        abstract int putLongLE(byte[] bytes, int offset, long val);
    }

    static class ConverterHolder {
        static final String UNSAFE_CONVERTER_NAME =
                ConverterHolder.class.getName() + "$UnsafeConverter";
        static final Converter BEST_CONVERTER = getBestConverter();

        static Converter getBestConverter() {
            try {
                Class<? extends Converter> theClass =
                        Class.forName(UNSAFE_CONVERTER_NAME).asSubclass(Converter.class);
                return theClass.getConstructor().newInstance();
            } catch (Throwable t) {
                return PureJavaConverter.INSTANCE;
            }
        }

        static final class PureJavaConverter extends Converter {
            static final PureJavaConverter INSTANCE = new PureJavaConverter();

            private PureJavaConverter() {
            }

            @Override
            int toIntLE(byte[] bytes, int offset) {
                int n = 0;
                for (int i = offset + 3; i >= offset; i--) {
                    n <<= 8;
                    n ^= (bytes[i] & 0xFF);
                }
                return n;
            }

            @Override
            int toIntLE(ByteBuffer buffer, int offset) {
                return Integer.reverseBytes(buffer.getInt(offset));
            }

            @Override
            int putIntLE(byte[] bytes, int offset, int val) {
                for (int i = offset; i < offset + 3; i++) {
                    bytes[i] = (byte) val;
                    val >>>= 8;
                }
                bytes[offset + 3] = (byte) val;
                return offset + Bytes.SIZEOF_INT;
            }

            @Override
            long toLongLE(byte[] bytes, int offset) {
                long l = 0;
                for (int i = offset + 7; i >= offset; i--) {
                    l <<= 8;
                    l ^= (bytes[i] & 0xFFL);
                }
                return l;
            }

            @Override
            long toLongLE(ByteBuffer buffer, int offset) {
                return Long.reverseBytes(buffer.getLong(offset));
            }

            @Override
            int putLongLE(byte[] bytes, int offset, long val) {
                for (int i = offset; i < offset + 7; i++) {
                    bytes[i] = (byte) val;
                    val >>>= 8;
                }
                bytes[offset + 7] = (byte) val;
                return offset + Bytes.SIZEOF_LONG;
            }
        }

        static final class UnsafeConverter extends Converter {
            static final UnsafeConverter INSTANCE = new UnsafeConverter();

            public UnsafeConverter() {
            }

            static {
                if (!UNSAFE_UNALIGNED) {
                    throw new Error();
                }
            }

            @Override
            int toIntLE(byte[] bytes, int offset) {
                return UnsafeAccess.toIntLE(bytes, offset);
            }

            @Override
            int toIntLE(ByteBuffer buffer, int offset) {
                return UnsafeAccess.toIntLE(buffer, offset);
            }

            @Override
            int putIntLE(byte[] bytes, int offset, int val) {
                return UnsafeAccess.putIntLE(bytes, offset, val);
            }

            @Override
            long toLongLE(byte[] bytes, int offset) {
                return UnsafeAccess.toLongLE(bytes, offset);
            }

            @Override
            long toLongLE(ByteBuffer buffer, int offset) {
                return UnsafeAccess.toLongLE(buffer, offset);
            }

            @Override
            int putLongLE(byte[] bytes, int offset, long val) {
                return UnsafeAccess.putLongLE(bytes, offset, val);
            }
        }
    }

    /*
     * Writes an int in little-endian order.
     * Caller must ensure bounds; no checks are performed.
     */
    public static void putIntLE(byte[] bytes, int offset, int val) {
        assert offset >= 0 && bytes.length - offset >= Bytes.SIZEOF_INT;
        ConverterHolder.BEST_CONVERTER.putIntLE(bytes, offset, val);
    }

    /*
     * Reads an int in little-endian order.
     * Caller must ensure bounds; no checks are performed.
     */
    public static int toIntLE(byte[] bytes, int offset) {
        assert offset >= 0 && bytes.length - offset >= Bytes.SIZEOF_INT;
        return ConverterHolder.BEST_CONVERTER.toIntLE(bytes, offset);
    }

    /*
     * Reads an int in little-endian order from ByteBuffer.
     * Caller must ensure bounds; no checks are performed.
     */
    public static int toIntLE(ByteBuffer buffer, int offset) {
        assert offset >= 0 && buffer.capacity() - offset >= Bytes.SIZEOF_INT;
        return ConverterHolder.BEST_CONVERTER.toIntLE(buffer, offset);
    }

    /*
     * Writes a long in little-endian order.
     * Caller must ensure bounds; no checks are performed.
     */
    public static void putLongLE(byte[] bytes, int offset, long val) {
        assert offset >= 0 && bytes.length - offset >= Bytes.SIZEOF_LONG;
        ConverterHolder.BEST_CONVERTER.putLongLE(bytes, offset, val);
    }

    /*
     * Reads a long in little-endian order.
     * Caller must ensure bounds; no checks are performed.
     */
    public static long toLongLE(byte[] bytes, int offset) {
        assert offset >= 0 && bytes.length - offset >= Bytes.SIZEOF_LONG;
        return ConverterHolder.BEST_CONVERTER.toLongLE(bytes, offset);
    }

    /*
     * Reads a long in little-endian order from ByteBuffer.
     * Caller must ensure bounds; no checks are performed.
     */
    public static long toLongLE(ByteBuffer buffer, int offset) {
        assert offset >= 0 && buffer.capacity() - offset >= Bytes.SIZEOF_LONG;
        return ConverterHolder.BEST_CONVERTER.toLongLE(buffer, offset);
    }

    public static int getRowAsIntLE(Cell cell, int offset) {
        if (cell instanceof ByteBufferExtendedCell) {
            ByteBufferExtendedCell bbCell = (ByteBufferExtendedCell) cell;
            return toIntLE(bbCell.getRowByteBuffer(), bbCell.getRowPosition() + offset);
        }
        return toIntLE(cell.getRowArray(), cell.getRowOffset() + offset);
    }

    public static long getRowAsLongLE(Cell cell, int offset) {
        if (cell instanceof ByteBufferExtendedCell) {
            ByteBufferExtendedCell bbCell = (ByteBufferExtendedCell) cell;
            return toLongLE(bbCell.getRowByteBuffer(), bbCell.getRowPosition() + offset);
        }
        return toLongLE(cell.getRowArray(), cell.getRowOffset() + offset);
    }

    public static int getQualifierAsIntLE(Cell cell, int offset) {
        if (cell instanceof ByteBufferExtendedCell) {
            ByteBufferExtendedCell bbCell = (ByteBufferExtendedCell) cell;
            return toIntLE(bbCell.getQualifierByteBuffer(), bbCell.getQualifierPosition() + offset);
        }
        return toIntLE(cell.getQualifierArray(), cell.getQualifierOffset() + offset);
    }

    public static long getQualifierAsLongLE(Cell cell, int offset) {
        if (cell instanceof ByteBufferExtendedCell) {
            ByteBufferExtendedCell bbCell = (ByteBufferExtendedCell) cell;
            return toLongLE(bbCell.getQualifierByteBuffer(), bbCell.getQualifierPosition() + offset);
        }
        return toLongLE(cell.getQualifierArray(), cell.getQualifierOffset() + offset);
    }

    private LittleEndianBytes() {
    }
}
