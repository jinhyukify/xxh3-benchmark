package hash;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class JenkinsGoldenTest {
    private static final String RESOURCE = "jenkins/jenkins_vectors.csv";
    private JenkinsHash jenkinsHash = new JenkinsHash();
    private JenkinsHash_V2 jenkinsHashV2 = new JenkinsHash_V2();

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
        InputStream is = JenkinsGoldenTest.class.getClassLoader().getResourceAsStream(RESOURCE);
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
    void testJenkins(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }

        final ByteArrayHashKey key = new ByteArrayHashKey(buf, 0, buf.length);
        long actual = jenkinsHash.hash(key, c.seed);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }

    @ParameterizedTest
    @MethodSource("vectors")
    void testJenkinsV2(Case c) {
        byte[] buf = new byte[c.len];
        for (int i = 0; i < c.len; i++) {
            buf[i] = (byte) i;
        }

        final ByteArrayHashKey key = new ByteArrayHashKey(buf, 0, buf.length);
        long actual = jenkinsHashV2.hash(key, c.seed);
        assertEquals(c.expected, actual, "len=" + c.len + " seed=" + c.seed);
    }
}
