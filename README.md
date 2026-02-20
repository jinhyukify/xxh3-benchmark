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
Benchmark                                                    (length)   Mode  Cnt          Score          Error   Units
XXH3BenchmarkTest.xxh3                                              3  thrpt    4  561040603.607 ± 57878439.005   ops/s
XXH3BenchmarkTest.xxh3:async                                        3  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                                3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                           3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                     3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                              8  thrpt    4  723900933.628 ±  8285665.303   ops/s
XXH3BenchmarkTest.xxh3:async                                        8  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                                8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                           8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                     8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                             16  thrpt    4  514256645.405 ±  4904477.585   ops/s
XXH3BenchmarkTest.xxh3:async                                       16  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                               16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                          16  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                    16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                             32  thrpt    4  408462260.076 ±  6424599.731   ops/s
XXH3BenchmarkTest.xxh3:async                                       32  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                               32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                          32  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                    32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                             64  thrpt    4  255487662.009 ±  6579959.784   ops/s
XXH3BenchmarkTest.xxh3:async                                       64  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                               64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                          64  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                    64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                            128  thrpt    4  137947015.645 ±  2242142.385   ops/s
XXH3BenchmarkTest.xxh3:async                                      128  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                              128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                         128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                   128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                            240  thrpt    4   66162458.453 ±  4809525.659   ops/s
XXH3BenchmarkTest.xxh3:async                                      240  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                              240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                         240  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                   240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                            256  thrpt    4   50493001.283 ±   513998.581   ops/s
XXH3BenchmarkTest.xxh3:async                                      256  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                              256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                         256  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                   256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                            512  thrpt    4   30813452.089 ±   444213.794   ops/s
XXH3BenchmarkTest.xxh3:async                                      512  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                              512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                         512  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                   512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                           1024  thrpt    4   17202710.376 ±   519969.058   ops/s
XXH3BenchmarkTest.xxh3:async                                     1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                             1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                        1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                  1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                           2048  thrpt    4    9129828.813 ±   390071.351   ops/s
XXH3BenchmarkTest.xxh3:async                                     2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                             2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                        2048  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                  2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                           4096  thrpt    4    4438780.799 ±   320548.955   ops/s
XXH3BenchmarkTest.xxh3:async                                     4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                             4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                        4096  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3:gc.count                                  4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3                                          16384  thrpt    4    1053383.323 ±    10975.111   ops/s
XXH3BenchmarkTest.xxh3:async                                    16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3:gc.alloc.rate                            16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3:gc.alloc.rate.norm                       16384  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.xxh3:gc.count                                 16384  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                          3  thrpt    4  271045432.367 ±  3816554.848   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                    3  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                            3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                       3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                                 3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                          8  thrpt    4  314085816.703 ±  2859922.009   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                    8  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                            8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                       8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                                 8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                         16  thrpt    4  270738027.319 ±  3757604.917   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                   16  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                           16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                      16  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                                16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                         32  thrpt    4  214476413.043 ±  8858460.489   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                   32  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                           32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                      32  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                                32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                         64  thrpt    4  146678023.676 ±  1039719.676   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                   64  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                           64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                      64  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                                64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                        128  thrpt    4   78176028.576 ±  1825186.601   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                  128  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                          128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                     128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                               128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                        240  thrpt    4   37213151.335 ±   395780.997   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                  240  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                          240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                     240  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                               240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                        256  thrpt    4   43642817.810 ±   620543.238   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                  256  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                          256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                     256  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                               256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                        512  thrpt    4   27664382.665 ±  1060097.336   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                  512  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                          512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                     512  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                               512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                       1024  thrpt    4   15904365.409 ±   126429.715   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                 1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                         1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                    1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                              1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                       2048  thrpt    4    7757026.170 ±   142947.868   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                 2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                         2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                    2048  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                              2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                       4096  thrpt    4    3897954.325 ±  1224781.377   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                 4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                         4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                    4096  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                              4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_ZAH                                      16384  thrpt    4    1008596.255 ±   386688.336   ops/s
XXH3BenchmarkTest.xxh3_ZAH:async                                16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate                        16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_ZAH:gc.alloc.rate.norm                   16384  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_ZAH:gc.count                             16384  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j                                       3  thrpt    4   54745998.501 ±  7790551.373   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                                 3  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                         3  thrpt    4      20048.307 ±     2853.310  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                    3  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                              3  thrpt    4       1366.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                               3  thrpt    4        599.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                       8  thrpt    4   58506433.477 ±  5824865.800   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                                 8  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                         8  thrpt    4      21425.456 ±     2133.067  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                    8  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                              8  thrpt    4       1367.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                               8  thrpt    4        607.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                      16  thrpt    4   59926450.257 ±  3327060.814   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                                16  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                        16  thrpt    4      21945.290 ±     1219.630  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                   16  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                             16  thrpt    4       1339.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                              16  thrpt    4        595.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                      32  thrpt    4   52383990.202 ± 12775098.546   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                                32  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                        32  thrpt    4      19183.051 ±     4679.613  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                   32  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                             32  thrpt    4       1143.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                              32  thrpt    4        525.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                      64  thrpt    4   40235270.301 ±   743090.251   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                                64  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                        64  thrpt    4      14734.428 ±      272.141  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                   64  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                             64  thrpt    4       1366.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                              64  thrpt    4        567.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                     128  thrpt    4   44168096.325 ±   908039.957   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                               128  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                       128  thrpt    4      16174.611 ±      332.765  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                  128  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                            128  thrpt    4       1473.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                             128  thrpt    4        613.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                     240  thrpt    4   26343837.942 ±   404838.962   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                               240  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                       240  thrpt    4       9647.272 ±      148.353  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                  240  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                            240  thrpt    4       1086.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                             240  thrpt    4        449.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                     256  thrpt    4   23001580.465 ±   334627.198   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                               256  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                       256  thrpt    4       8423.329 ±      122.516  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                  256  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                            256  thrpt    4        917.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                             256  thrpt    4        377.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                     512  thrpt    4    6733950.346 ±    60646.285   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                               512  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                       512  thrpt    4       2465.998 ±       22.313  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                  512  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                            512  thrpt    4        441.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                             512  thrpt    4        165.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                    1024  thrpt    4    3801769.811 ±    43402.003   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                              1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                      1024  thrpt    4       1392.233 ±       15.906  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                 1024  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                           1024  thrpt    4        249.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                            1024  thrpt    4         94.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                    2048  thrpt    4    1567339.777 ±    20616.387   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                              2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                      2048  thrpt    4        573.966 ±        7.579  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                 2048  thrpt    4        384.000 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                           2048  thrpt    4        103.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                            2048  thrpt    4         39.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                    4096  thrpt    4     812342.205 ±     1448.295   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                              4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                      4096  thrpt    4        297.486 ±        0.529  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                 4096  thrpt    4        384.001 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                           4096  thrpt    4         53.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                            4096  thrpt    4         20.000                     ms
XXH3BenchmarkTest.xxh3_hash4j                                   16384  thrpt    4     225262.135 ±    35624.160   ops/s
XXH3BenchmarkTest.xxh3_hash4j:async                             16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate                     16384  thrpt    4         82.492 ±       13.046  MB/sec
XXH3BenchmarkTest.xxh3_hash4j:gc.alloc.rate.norm                16384  thrpt    4        384.003 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j:gc.count                          16384  thrpt    4         15.000                 counts
XXH3BenchmarkTest.xxh3_hash4j:gc.time                           16384  thrpt    4          7.000                     ms
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                            3  thrpt    4  292710354.484 ±  2686605.394   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                      3  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate              3  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm         3  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                   3  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                            8  thrpt    4  339648842.202 ±  1040836.303   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                      8  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate              8  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm         8  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                   8  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                           16  thrpt    4  325043347.888 ±  5121144.272   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                     16  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate             16  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm        16  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                  16  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                           32  thrpt    4  261341088.353 ±  5053327.149   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                     32  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate             32  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm        32  thrpt    4         ≈ 10⁻⁶                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                  32  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                           64  thrpt    4  197250850.156 ±  1365547.980   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                     64  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate             64  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm        64  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                  64  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                          128  thrpt    4  127985661.113 ±  6753956.305   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                    128  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate            128  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm       128  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                 128  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                          240  thrpt    4   59892463.727 ±  1077266.031   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                    240  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate            240  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm       240  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                 240  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                          256  thrpt    4   50955504.933 ±   395275.665   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                    256  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate            256  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm       256  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                 256  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                          512  thrpt    4   30702766.259 ±   203656.427   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                    512  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate            512  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm       512  thrpt    4         ≈ 10⁻⁵                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                 512  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                         1024  thrpt    4   16899964.682 ±   153488.650   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                   1024  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate           1024  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm      1024  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                1024  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                         2048  thrpt    4    8421443.449 ±   102966.934   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                   2048  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate           2048  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm      2048  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                2048  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                         4096  thrpt    4    4398589.229 ±   136450.392   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                   4096  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate           4096  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm      4096  thrpt    4         ≈ 10⁻⁴                   B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count                4096  thrpt    4            ≈ 0                 counts
XXH3BenchmarkTest.xxh3_hash4j_byteAccess                        16384  thrpt    4    1132037.515 ±    43411.962   ops/s
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:async                  16384  thrpt                 NaN                    ---
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate          16384  thrpt    4          0.001 ±        0.001  MB/sec
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.alloc.rate.norm     16384  thrpt    4          0.001 ±        0.001    B/op
XXH3BenchmarkTest.xxh3_hash4j_byteAccess:gc.count               16384  thrpt    4            ≈ 0                 counts
```
