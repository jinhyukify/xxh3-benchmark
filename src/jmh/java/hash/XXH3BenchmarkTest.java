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
    @Param({ "3", "8", "16", "32", "64", "128", "240", "256", "512", "1024", "2048", "4096", "16384" })
    public int length;

    byte[] data;
    RowBloomHashKey key;
    ByteBuffer b;
    private XXH3 xxh3;
    private JenkinsHash jenkinsHash;
    private JenkinsHash_V2 jenkinsHashV2;
    private MurmurHash murmurHash;
    private MurmurHash_V2 murmurHashV2;
    private MurmurHash3 murmurHash3;
    private MurMurHash3_V2 murmurHash3V2;

    @Setup
    public void setup() {
        data = new byte[length];
        new Random().nextBytes(data);
        b = ByteBuffer.wrap(data);
        KeyValue kv = new KeyValue(data, Bytes.toBytes("f"), Bytes.toBytes("q"), HConstants.EMPTY_BYTE_ARRAY);
        key = new RowBloomHashKey(kv);
        xxh3 = new XXH3();
        jenkinsHash = new JenkinsHash();
        jenkinsHashV2 = new JenkinsHash_V2();
        murmurHash = new MurmurHash();
        murmurHashV2 = new MurmurHash_V2();
        murmurHash3 = new MurmurHash3();
        murmurHash3V2 = new MurMurHash3_V2();
    }


    @Benchmark
    public int murmur3() {
        return murmurHash3.hash(key, 0);
    }

    @Benchmark
    public int murmur3_after() {
        return murmurHash3V2.hash(key, 0);
    }

    @Benchmark
    public long murmur() {
        return murmurHash.hash(key, 0);
    }

    @Benchmark
    public long murmur_after() {
        return murmurHashV2.hash(key, 0);
    }

    @Benchmark
    public long jenkins() {
        return jenkinsHash.hash(key, 0);
    }

    @Benchmark
    public long jenkins_after() {
        return jenkinsHashV2.hash(key, 0);
    }
}
