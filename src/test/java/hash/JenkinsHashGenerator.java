package hash;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class JenkinsHashGenerator {
    private static final int SEED = 31;

    @Test
    public void generateCsv() throws IOException {
        Hash hash = JenkinsHash.getInstance();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jenkins_vectors.csv"))) {
            for (int len = 0; len < 2050; len++) {
                writer.write(Integer.toString(len));
                writer.write(",");
                writer.write("0");
                writer.write(",");
                byte[] buf = new byte[len];
                for (int c = 0; c < len; c++) {
                    buf[c] = (byte) c;
                }
                final ByteArrayHashKey hashKey = new ByteArrayHashKey(buf, 0, buf.length);
                writer.write(Integer.toString(hash.hash(hashKey, 0)));
                writer.write(",");
                writer.write(Integer.toString(SEED));
                writer.write(",");
                writer.write(Integer.toString(hash.hash(hashKey, SEED)));
                writer.newLine();
            }

            int seed = 0;
            for (int len = 2050; len < 3050; len++) {
                writer.write(Integer.toString(len));
                writer.write(",");
                writer.write(Integer.toString(seed));
                writer.write(",");
                byte[] buf = new byte[len];
                for (int c = 0; c < len; c++) {
                    buf[c] = (byte) c;
                }
                final ByteArrayHashKey hashKey = new ByteArrayHashKey(buf, 0, buf.length);
                int hashValue = hash.hash(hashKey, seed);
                writer.write(Integer.toString(hashValue));
                writer.write(",");
                seed = hashValue;
                writer.write(Integer.toString(seed));
                writer.write(",");
                writer.write(Integer.toString(hash.hash(hashKey, seed)));
                writer.newLine();
            }
        }
    }
}
