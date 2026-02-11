package hash;

public abstract class HashKey<T> {
    protected final T t;

    public HashKey(T t) {
        this.t = t;
    }

    /** Return The byte at the given position in this HashKey */
    public abstract byte get(int pos);

    /** Returns The number of bytes in this HashKey */
    public abstract int length();

    public abstract int getIntLE(int pos);

    public abstract long getLongLE(int pos);
}
