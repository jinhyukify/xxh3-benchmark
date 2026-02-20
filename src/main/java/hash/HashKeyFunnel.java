package hash;

import com.dynatrace.hash4j.hashing.HashFunnel;
import com.dynatrace.hash4j.hashing.HashSink;

public class HashKeyFunnel implements HashFunnel<HashKey<?>> {
    public static final HashKeyFunnel INSTANCE = new HashKeyFunnel();

    @Override
    public void put(HashKey<?> key, HashSink sink) {
        int len = key.length();
        int pos = 0;

        while (pos + Long.BYTES <= len) {
            long v = key.getLongLE(pos);
            sink.putLong(v);
            pos += Long.BYTES;
        }

        if (pos + Integer.BYTES <= len) {
            int v = key.getIntLE(pos);
            sink.putInt(v);
            pos += Integer.BYTES;
        }

        while (pos < len) {
            sink.putByte(key.get(pos));
            pos++;
        }
    }
}
