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

The benchmark results are documented in the following document:
[Benchmark Results](https://docs.google.com/document/d/1LycZZMKFrrxYytEnzVj-EjQB4PbmmTgprhMOpDRPqYM/edit?usp=sharing)
