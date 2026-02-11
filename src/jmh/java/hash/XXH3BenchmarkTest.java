package hash;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
public class XXH3BenchmarkTest {
    @Param({ "16" })
//    @Param({ "16", "32", "64", "128", "240", "256", "512", "1024", "2048", "4096", "16384" })
    public int length;

    ByteArrayHashKey key;
    RowColBloomHashKey rowColKey;
    ByteBuffer b;
    private SlowPathXXH3 slowPathXXH3;
    private XXH3 XXH3;
    private JenkinsHash jenkinsHash;
    private MurmurHash murmurHash;
    private MurmurHash3 murmurHash3;

    @Setup
    public void setup() {
        byte[] data = new byte[length];
        new Random().nextBytes(data);
        final int rowcolLength = (length - 2 - 1 - 8 - 1) / 2;
        byte[] rowKeyData = new byte[rowcolLength];
        new Random().nextBytes(rowKeyData);
        byte[] qualData = new byte[rowcolLength];
        new Random().nextBytes(qualData);
        KeyValue kv = new KeyValue(rowKeyData, Bytes.toBytes("f"), qualData, HConstants.EMPTY_BYTE_ARRAY);
        rowColKey = new RowColBloomHashKey(kv);

        key = new ByteArrayHashKey(data, 0, data.length);
        b = ByteBuffer.wrap(data);
        slowPathXXH3 = new SlowPathXXH3();
        XXH3 = new XXH3();
        jenkinsHash = new JenkinsHash();
        murmurHash = new MurmurHash();
        murmurHash3 = new MurmurHash3();
    }

    @Benchmark
    public long xxh3_slow() {
        return slowPathXXH3.hash(key, 0);
    }

    @Benchmark
    public long xxh3() {
        return XXH3.hash(key, 0);
    }

    @Benchmark
    public long jenkins() {
        return jenkinsHash.hash(key, 0);
    }

    @Benchmark
    public long murmur() {
        return murmurHash.hash(key, 0);
    }

    @Benchmark
    public int murmur3() {
        return murmurHash3.hash(key, 0);
    }

    @Benchmark
    public long rowcol_jenkins() {
        return jenkinsHash.hash(rowColKey, 0);
    }

    @Benchmark
    public long rowcol_murmur() {
        return murmurHash.hash(rowColKey, 0);
    }

    @Benchmark
    public int rowcol_murmur3() {
        return murmurHash3.hash(rowColKey, 0);
    }

    @Benchmark
    public long rowcol_xxh3_slow() {
        return slowPathXXH3.hash(rowColKey, 0);
    }

    @Benchmark
    public long rowcol_xxh3() {
        return XXH3.hash(rowColKey, 0);
    }
}
