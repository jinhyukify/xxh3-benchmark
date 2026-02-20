package hash;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import net.openhft.hashing.LongHashFunction;

import com.dynatrace.hash4j.hashing.Hasher64;
import com.dynatrace.hash4j.hashing.Hashing;

public class XXH3GoldenTest {
    private static final String RESOURCE = "xxh3/xxh3_vectors.csv";
    private XXH3 xxh3 = new XXH3();
    private Hasher64 hash4jHasher = Hashing.xxh3_64();
    private Hasher64 hash4jHasher31 = Hashing.xxh3_64(31L);

    static class Case {
        final int len;
        final long seed;
        final long expected;

        Case(int len, long seed, long expected) {
            this.len = len;
            this.seed = seed;
            this.expected = expected;
        }
    }

    static Stream<Case> vectors() throws Exception {
        InputStream is = XXH3GoldenTest.class.getClassLoader().getResourceAsStream(RESOURCE);
        if (is == null) {
            throw new IllegalStateException(RESOURCE + " not found in resources");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            List<Case> out = new ArrayList<>(4096);

            br.lines().filter(s -> !s.isEmpty()).forEach(line -> {
                String[] p = line.split(",");
                int len = Integer.parseInt(p[0]);

                for (int i = 1; i + 1 < p.length; i += 2) {
                    long seed = Long.parseLong(p[i]);
                    long expected = Long.parseLong(p[i + 1]);
                    out.add(new Case(len, seed, expected));
                }
            });

            return out.stream();
        }
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testXxh3(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }

        final ByteArrayHashKey key = new ByteArrayHashKey(buf, 0, buf.length);
        long actual = xxh3.hash64(key, c.seed);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testXxh3ZAH(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }

        final ByteArrayHashKey key = new ByteArrayHashKey(buf, 0, buf.length);
        long actual = LongHashFunction.xx3(c.seed).hash(key, AccessFactory.HASHKEY_ACCESS, 0, buf.length);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testXxh3Hash4j(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }
        KeyValue kv = new KeyValue(buf, Bytes.toBytes("f"), Bytes.toBytes("q"), HConstants.EMPTY_BYTE_ARRAY);
        RowBloomHashKey rowKey = new RowBloomHashKey(kv);

        Hasher64 hasher;
        if (c.seed == 31L) {
            hasher = hash4jHasher31;
        } else {
            hasher = hash4jHasher;
        }
        long actual = hasher.hashToLong(rowKey, HashKeyFunnel.INSTANCE);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testXxh3Hash4jByteAccess(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }
        KeyValue kv = new KeyValue(buf, Bytes.toBytes("f"), Bytes.toBytes("q"), HConstants.EMPTY_BYTE_ARRAY);
        RowBloomHashKey rowKey = new RowBloomHashKey(kv);

        Hasher64 hasher;
        if (c.seed == 31L) {
            hasher = hash4jHasher31;
        } else {
            hasher = hash4jHasher;
        }
        long actual = hasher.hashBytesToLong(rowKey, 0, rowKey.length(), HashKeyByteAccess.INSTANCE);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }
}
