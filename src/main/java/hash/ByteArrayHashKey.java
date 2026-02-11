package hash;

public class ByteArrayHashKey extends HashKey<byte[]> {

    private final int offset;
    private final int length;

    public ByteArrayHashKey(byte[] t, int offset, int length) {
        super(t);
        this.offset = offset;
        this.length = length;
    }

    @Override
    public byte get(int pos) {
        return t[getAbsolutePos(pos)];
    }

    private int getAbsolutePos(int pos) {
        return this.offset + pos;
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public int getIntLE(int pos) {
        return LittleEndianBytes.toIntLE(t, getAbsolutePos(pos));
    }

    @Override
    public long getLongLE(int pos) {
        return LittleEndianBytes.toLongLE(t, getAbsolutePos(pos));
    }
}

