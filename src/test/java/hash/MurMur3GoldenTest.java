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

import com.dynatrace.hash4j.hashing.Hasher32;
import com.dynatrace.hash4j.hashing.Hashing;

public class MurMur3GoldenTest {
    private static final String RESOURCE = "murmur3/murmur3_vectors.csv";
    private MurmurHash3 murmurHash3 = new MurmurHash3();
    private Hasher32 hash4jHasher = Hashing.murmur3_32();

    static class Case {
        final int len;
        final int seed;
        final int expected;

        Case(int len, int seed, int expected) {
            this.len = len;
            this.seed = seed;
            this.expected = expected;
        }
    }

    static Stream<Case> vectors() throws Exception {
        InputStream is = MurMur3GoldenTest.class.getClassLoader().getResourceAsStream(RESOURCE);
        if (is == null) {
            throw new IllegalStateException(RESOURCE + " not found in resources");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            List<Case> out = new ArrayList<>(4096);

            br.lines().filter(s -> !s.isEmpty()).forEach(line -> {
                String[] p = line.split(",");
                int len = Integer.parseInt(p[0]);

                for (int i = 1; i + 1 < p.length; i += 2) {
                    int seed = Integer.parseInt(p[i]);
                    int expected = Integer.parseInt(p[i + 1]);
                    out.add(new Case(len, seed, expected));
                }
            });

            return out.stream();
        }
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testMurMur3(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }

        final ByteArrayHashKey key = new ByteArrayHashKey(buf, 0, buf.length);
        long actual = murmurHash3.hash(key, c.seed);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testMurMur3Hash4j(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }
        KeyValue kv = new KeyValue(buf, Bytes.toBytes("f"), Bytes.toBytes("q"), HConstants.EMPTY_BYTE_ARRAY);
        RowBloomHashKey rowKey = new RowBloomHashKey(kv);

        Hasher32 hasher;
        if (c.seed == 0) {
            hasher = hash4jHasher;
        } else {
            hasher = Hashing.murmur3_32(c.seed);
        }

        long actual = hasher.hashBytesToInt(rowKey, 0, rowKey.length(), HashKeyByteAccess.INSTANCE);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testXxh3Hash4jByteAccess(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }

        ByteArrayHashKey key = new ByteArrayHashKey(buf, 0, buf.length);

        Hasher32 hasher;
        if (c.seed == 0) {
            hasher = hash4jHasher;
        } else {
            hasher = Hashing.murmur3_32(c.seed);
        }

        long actual = hasher.hashBytesToInt(key, 0, key.length(), HashKeyByteAccess.INSTANCE);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }
}
