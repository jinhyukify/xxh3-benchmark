# XXH3 Benchmark Tests

This project contains JMH benchmarks for comparing different hashing algorithms  
(XXH3, Jenkins, MurmurHash, MurmurHash3, and Row/RowCol variants used in HBase).

The goal is to measure the throughput of each hashing implementation across  
different input lengths and key structures.

---

## 🚀 How to Run

### 1. Clean the project
```bash
./gradlew clean
```

### 2. Run the benchmarks
```bash
./gradlew jmh
```

All benchmarks under `src/jmh/java` will be executed automatically.

Benchmark results will appear in the terminal.
JMH result files will also be saved under:
`build/reports/jmh/`

## Benchmark Results

- Tested on Macbook (Apple M4 Pro)

```
Benchmark                                         (length)   Mode  Cnt          Score          Error   Units
XXH3BenchmarkTest.jenkins                                3  thrpt    4  368564088.553 ±  3269401.184   ops/s
XXH3BenchmarkTest.jenkins:async                          3  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                  3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm             3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.jenkins:gc.count                       3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                                8  thrpt    4  330039144.293 ± 16773851.392   ops/s
XXH3BenchmarkTest.jenkins:async                          8  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                  8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm             8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.jenkins:gc.count                       8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                               16  thrpt    4  150274618.621 ±  4143856.157   ops/s
XXH3BenchmarkTest.jenkins:async                         16  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                 16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm            16  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.jenkins:gc.count                      16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                               32  thrpt    4   98705675.752 ±  4557075.687   ops/s
XXH3BenchmarkTest.jenkins:async                         32  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                 32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm            32  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.jenkins:gc.count                      32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                               64  thrpt    4   52293605.511 ±  1179889.596   ops/s
XXH3BenchmarkTest.jenkins:async                         64  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                 64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm            64  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.jenkins:gc.count                      64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                              128  thrpt    4   24074192.667 ±  5635167.421   ops/s
XXH3BenchmarkTest.jenkins:async                        128  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm           128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.jenkins:gc.count                     128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                              240  thrpt    4   12873068.777 ±  1081744.088   ops/s
XXH3BenchmarkTest.jenkins:async                        240  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm           240  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.jenkins:gc.count                     240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                              256  thrpt    4   11923179.880 ±   479252.581   ops/s
XXH3BenchmarkTest.jenkins:async                        256  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm           256  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.jenkins:gc.count                     256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                              512  thrpt    4    5935503.890 ±   307765.041   ops/s
XXH3BenchmarkTest.jenkins:async                        512  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate                512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm           512  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.jenkins:gc.count                     512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                             1024  thrpt    4    2968671.251 ±   270419.448   ops/s
XXH3BenchmarkTest.jenkins:async                       1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate               1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm          1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.jenkins:gc.count                    1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                             2048  thrpt    4    1462807.687 ±   209852.664   ops/s
XXH3BenchmarkTest.jenkins:async                       2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate               2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm          2048  thrpt    4         ≈ 10⁻³                   B/op
XXH3BenchmarkTest.jenkins:gc.count                    2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                             4096  thrpt    4     718877.668 ±    77552.810   ops/s
XXH3BenchmarkTest.jenkins:async                       4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate               4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm          4096  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.jenkins:gc.count                    4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.jenkins                            16384  thrpt    4     175235.387 ±    89076.404   ops/s
XXH3BenchmarkTest.jenkins:async                      16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.jenkins:gc.alloc.rate              16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.jenkins:gc.alloc.rate.norm         16384  thrpt    4          0.004 ±        0.002    B/op
XXH3BenchmarkTest.jenkins:gc.count                   16384  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                                 3  thrpt    4  589943065.613 ± 58802941.813   ops/s
XXH3BenchmarkTest.murmur:async                           3  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                   3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm              3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.murmur:gc.count                        3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                                 8  thrpt    4  316932185.394 ±  6593006.538   ops/s
XXH3BenchmarkTest.murmur:async                           8  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                   8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm              8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.murmur:gc.count                        8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                                16  thrpt    4  243819055.421 ± 12616012.501   ops/s
XXH3BenchmarkTest.murmur:async                          16  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                  16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm             16  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.murmur:gc.count                       16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                                32  thrpt    4  146320874.920 ±  5052271.543   ops/s
XXH3BenchmarkTest.murmur:async                          32  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                  32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm             32  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur:gc.count                       32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                                64  thrpt    4   85006764.578 ±   984944.188   ops/s
XXH3BenchmarkTest.murmur:async                          64  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                  64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm             64  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur:gc.count                       64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                               128  thrpt    4   44414812.546 ±   633293.052   ops/s
XXH3BenchmarkTest.murmur:async                         128  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                 128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm            128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur:gc.count                      128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                               240  thrpt    4   23466502.351 ±   426426.189   ops/s
XXH3BenchmarkTest.murmur:async                         240  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                 240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm            240  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur:gc.count                      240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                               256  thrpt    4   21809855.645 ±   594808.092   ops/s
XXH3BenchmarkTest.murmur:async                         256  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                 256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm            256  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur:gc.count                      256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                               512  thrpt    4   10156749.861 ±   164150.278   ops/s
XXH3BenchmarkTest.murmur:async                         512  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                 512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm            512  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.murmur:gc.count                      512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                              1024  thrpt    4    4594560.998 ±   226567.520   ops/s
XXH3BenchmarkTest.murmur:async                        1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm           1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.murmur:gc.count                     1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                              2048  thrpt    4    2203937.175 ±    32004.577   ops/s
XXH3BenchmarkTest.murmur:async                        2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm           2048  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.murmur:gc.count                     2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                              4096  thrpt    4    1038801.509 ±    59150.478   ops/s
XXH3BenchmarkTest.murmur:async                        4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate                4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm           4096  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.murmur:gc.count                     4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur                             16384  thrpt    4     257554.255 ±     9462.941   ops/s
XXH3BenchmarkTest.murmur:async                       16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur:gc.alloc.rate               16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur:gc.alloc.rate.norm          16384  thrpt    4          0.003 ±        0.001    B/op
XXH3BenchmarkTest.murmur:gc.count                    16384  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                                3  thrpt    4  628432036.259 ± 22505375.269   ops/s
XXH3BenchmarkTest.murmur3:async                          3  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                  3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm             3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.murmur3:gc.count                       3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                                8  thrpt    4  277251285.862 ±  5512667.041   ops/s
XXH3BenchmarkTest.murmur3:async                          8  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                  8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm             8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.murmur3:gc.count                       8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                               16  thrpt    4  208646557.828 ±  6484494.740   ops/s
XXH3BenchmarkTest.murmur3:async                         16  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                 16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm            16  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur3:gc.count                      16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                               32  thrpt    4  129108467.975 ±  1221225.453   ops/s
XXH3BenchmarkTest.murmur3:async                         32  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                 32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm            32  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur3:gc.count                      32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                               64  thrpt    4   71168511.465 ±   639435.964   ops/s
XXH3BenchmarkTest.murmur3:async                         64  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                 64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm            64  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur3:gc.count                      64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                              128  thrpt    4   36360554.625 ±  2815784.937   ops/s
XXH3BenchmarkTest.murmur3:async                        128  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm           128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.murmur3:gc.count                     128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                              240  thrpt    4   17835090.771 ±  1062720.241   ops/s
XXH3BenchmarkTest.murmur3:async                        240  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm           240  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.murmur3:gc.count                     240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                              256  thrpt    4   16668624.974 ±   498915.366   ops/s
XXH3BenchmarkTest.murmur3:async                        256  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm           256  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.murmur3:gc.count                     256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                              512  thrpt    4    7593444.587 ±   132293.200   ops/s
XXH3BenchmarkTest.murmur3:async                        512  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate                512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm           512  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.murmur3:gc.count                     512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                             1024  thrpt    4    3566957.785 ±   189857.717   ops/s
XXH3BenchmarkTest.murmur3:async                       1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate               1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm          1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.murmur3:gc.count                    1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                             2048  thrpt    4    1690012.347 ±   134634.947   ops/s
XXH3BenchmarkTest.murmur3:async                       2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate               2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm          2048  thrpt    4         ≈ 10⁻³                   B/op
XXH3BenchmarkTest.murmur3:gc.count                    2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                             4096  thrpt    4     820992.767 ±    90758.599   ops/s
XXH3BenchmarkTest.murmur3:async                       4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate               4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm          4096  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.murmur3:gc.count                    4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.murmur3                            16384  thrpt    4     206871.033 ±     6961.888   ops/s
XXH3BenchmarkTest.murmur3:async                      16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.murmur3:gc.alloc.rate              16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.murmur3:gc.alloc.rate.norm         16384  thrpt    4          0.003 ±        0.001    B/op
XXH3BenchmarkTest.murmur3:gc.count                   16384  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                   3  thrpt    4  569428106.236 ±  6449565.484   ops/s
XXH3BenchmarkTest.xxh3:async                             3  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                     3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                          3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                   8  thrpt    4  720914580.983 ±  6211755.231   ops/s
XXH3BenchmarkTest.xxh3:async                             8  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                     8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                          8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                  16  thrpt    4  517416302.657 ±  8751241.329   ops/s
XXH3BenchmarkTest.xxh3:async                            16  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                    16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm               16  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                         16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                  32  thrpt    4  475791353.698 ±  8358108.380   ops/s
XXH3BenchmarkTest.xxh3:async                            32  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                    32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm               32  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                         32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                  64  thrpt    4  317977198.184 ±  3902090.923   ops/s
XXH3BenchmarkTest.xxh3:async                            64  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                    64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm               64  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                         64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                 128  thrpt    4  172098963.698 ±  6501456.754   ops/s
XXH3BenchmarkTest.xxh3:async                           128  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                   128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm              128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                        128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                 240  thrpt    4   84928121.626 ±  1636331.776   ops/s
XXH3BenchmarkTest.xxh3:async                           240  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                   240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm              240  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                        240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                 256  thrpt    4   52526841.736 ±   533683.484   ops/s
XXH3BenchmarkTest.xxh3:async                           256  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                   256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm              256  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                        256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                 512  thrpt    4   31593957.540 ±   566297.707   ops/s
XXH3BenchmarkTest.xxh3:async                           512  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                   512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm              512  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                        512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                1024  thrpt    4   17606315.365 ±   285033.293   ops/s
XXH3BenchmarkTest.xxh3:async                          1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                  1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm             1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3:gc.count                       1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                2048  thrpt    4    9266953.931 ±   396106.975   ops/s
XXH3BenchmarkTest.xxh3:async                          2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                  2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm             2048  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3:gc.count                       2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                4096  thrpt    4    4476948.273 ±   305214.871   ops/s
XXH3BenchmarkTest.xxh3:async                          4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                  4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm             4096  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3:gc.count                       4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                               16384  thrpt    4    1071176.922 ±    10426.035   ops/s
XXH3BenchmarkTest.xxh3:async                         16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                 16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm            16384  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.xxh3:gc.count                      16384  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                               3  thrpt    4  272432506.517 ±  6309388.421   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                         3  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                 3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm            3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                      3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                               8  thrpt    4  314827124.971 ±  7355277.963   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                         8  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                 8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm            8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                      8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                              16  thrpt    4  270834092.718 ±  4502848.079   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                        16  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm           16  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                     16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                              32  thrpt    4  216023769.692 ±  5695514.567   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                        32  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm           32  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                     32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                              64  thrpt    4  147082458.917 ±  1691734.329   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                        64  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm           64  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                     64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                             128  thrpt    4   79325847.999 ±  1064341.833   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                       128  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate               128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm          128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                    128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                             240  thrpt    4   37351667.330 ±   401048.745   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                       240  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate               240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm          240  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                    240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                             256  thrpt    4   43851149.394 ±   367781.334   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                       256  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate               256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm          256  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                    256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                             512  thrpt    4   27898183.688 ±   497662.332   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                       512  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate               512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm          512  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                    512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                            1024  thrpt    4   15958349.449 ±   290381.535   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                      1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate              1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm         1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                   1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                            2048  thrpt    4    7766243.696 ±   283773.617   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                      2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate              2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm         2048  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                   2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                            4096  thrpt    4    4033846.386 ±    55850.933   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                      4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate              4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm         4096  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                   4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                           16384  thrpt    4    1039163.325 ±    53413.713   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                     16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate             16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm        16384  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                  16384  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j                            3  thrpt    4   51301715.313 ±  7722699.198   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                      3  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate              3  thrpt    4      18786.965 ±     2828.203  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm         3  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                   3  thrpt    4       1238.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                    3  thrpt    4        525.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                            8  thrpt    4   53969746.437 ± 10595580.111   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                      8  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate              8  thrpt    4      19763.983 ±     3880.804  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm         8  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                   8  thrpt    4       1434.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                    8  thrpt    4        616.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                           16  thrpt    4   60247155.221 ± 12492162.992   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                     16  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate             16  thrpt    4      22062.742 ±     4575.099  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm        16  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                  16  thrpt    4       1493.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                   16  thrpt    4        658.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                           32  thrpt    4   49036049.617 ± 16847079.064   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                     32  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate             32  thrpt    4      17957.164 ±     6170.032  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm        32  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                  32  thrpt    4       1292.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                   32  thrpt    4        553.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                           64  thrpt    4   38376845.501 ±  3772041.945   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                     64  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate             64  thrpt    4      14053.824 ±     1380.927  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm        64  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                  64  thrpt    4       1316.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                   64  thrpt    4        546.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                          128  thrpt    4   44337072.074 ±  1633003.032   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                    128  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate            128  thrpt    4      16236.185 ±      599.400  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm       128  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                 128  thrpt    4       1291.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                  128  thrpt    4        559.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                          240  thrpt    4   20631455.469 ±   473376.765   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                    240  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate            240  thrpt    4       7555.294 ±      173.706  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm       240  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                 240  thrpt    4        735.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                  240  thrpt    4        318.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                          256  thrpt    4   22703411.736 ±   776701.259   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                    256  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate            256  thrpt    4       8314.081 ±      284.464  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm       256  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                 256  thrpt    4        841.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                  256  thrpt    4        360.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                          512  thrpt    4    6694643.060 ±   228208.719   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                    512  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate            512  thrpt    4       2451.560 ±       83.647  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm       512  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                 512  thrpt    4        439.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                  512  thrpt    4        180.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                         1024  thrpt    4    3672168.269 ±   345955.041   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                   1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate           1024  thrpt    4       1344.758 ±      126.804  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm      1024  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                1024  thrpt    4        241.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                 1024  thrpt    4        103.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                         2048  thrpt    4    1589402.290 ±    87484.874   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                   2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate           2048  thrpt    4        582.028 ±       32.306  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm      2048  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                2048  thrpt    4        104.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                 2048  thrpt    4         55.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                         4096  thrpt    4     811260.546 ±     7069.254   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                   4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate           4096  thrpt    4        297.087 ±        2.585  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm      4096  thrpt    4        384.001 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                4096  thrpt    4         54.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                 4096  thrpt    4         31.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                        16384  thrpt    4     226560.962 ±     6213.698   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                  16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate          16384  thrpt    4         82.968 ±        2.283  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm     16384  thrpt    4        384.003 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count               16384  thrpt    4         15.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                16384  thrpt    4          8.000                     ms
```
