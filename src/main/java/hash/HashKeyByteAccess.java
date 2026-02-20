package hash;

import com.dynatrace.hash4j.hashing.ByteAccess;

public class HashKeyByteAccess implements ByteAccess<HashKey<?>> {
    public static final HashKeyByteAccess INSTANCE = new HashKeyByteAccess();

    @Override
    public byte getByte(HashKey<?> data, long idx) {
        return data.get((int) idx);
    }

    @Override
    public int getInt(HashKey<?> data, long idx) {
        return data.getIntLE((int) idx);
    }

    @Override
    public long getLong(HashKey<?> data, long idx) {
        return data.getLongLE((int) idx);
    }
}
