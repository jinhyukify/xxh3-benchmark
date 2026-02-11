package hash;

import static java.lang.Integer.toUnsignedLong;
import static org.apache.hadoop.hbase.util.UnsafeAccess.BYTE_ARRAY_BASE_OFFSET;

import org.apache.hadoop.hbase.unsafe.HBasePlatformDependent;
import org.apache.hadoop.hbase.util.UnsafeAccess;

import com.google.common.annotations.VisibleForTesting;

public class SlowPathXXH3 extends Hash {
    private final static SlowPathXXH3 _instance = new SlowPathXXH3();

    public static Hash getInstance() {
        return _instance;
    }
    private static final long MASK32 = 0xFFFFFFFFL;
    private static final long PRIME32_1 = 0x9E3779B1L;
    private static final long PRIME32_2 = 0x85EBCA77L;
    private static final long PRIME32_3 = 0xC2B2AE3DL;
    private static final long PRIME64_1 = 0x9E3779B185EBCA87L;
    private static final long PRIME64_2 = 0xC2B2AE3D27D4EB4FL;
    private static final long PRIME64_3 = 0x165667B19E3779F9L;
    private static final long PRIME64_4 = 0x85EBCA77C2B2AE63L;
    private static final long PRIME64_5 = 0x27D4EB2F165667C5L;
    private static final long PRIME_MX1 = 0x165667919E3779F9L;
    private static final long PRIME_MX2 = 0x9FB21C651E98DF25L;

    private static final byte[] DEFAULT_SECRET = {
            (byte) 0xb8, (byte) 0xfe, (byte) 0x6c, (byte) 0x39, (byte) 0x23, (byte) 0xa4, (byte) 0x4b,
            (byte) 0xbe, (byte) 0x7c, (byte) 0x01, (byte) 0x81, (byte) 0x2c, (byte) 0xf7, (byte) 0x21,
            (byte) 0xad, (byte) 0x1c,
            (byte) 0xde, (byte) 0xd4, (byte) 0x6d, (byte) 0xe9, (byte) 0x83, (byte) 0x90, (byte) 0x97,
            (byte) 0xdb, (byte) 0x72, (byte) 0x40, (byte) 0xa4, (byte) 0xa4, (byte) 0xb7, (byte) 0xb3,
            (byte) 0x67, (byte) 0x1f,
            (byte) 0xcb, (byte) 0x79, (byte) 0xe6, (byte) 0x4e, (byte) 0xcc, (byte) 0xc0, (byte) 0xe5,
            (byte) 0x78, (byte) 0x82, (byte) 0x5a, (byte) 0xd0, (byte) 0x7d, (byte) 0xcc, (byte) 0xff,
            (byte) 0x72, (byte) 0x21,
            (byte) 0xb8, (byte) 0x08, (byte) 0x46, (byte) 0x74, (byte) 0xf7, (byte) 0x43, (byte) 0x24,
            (byte) 0x8e, (byte) 0xe0, (byte) 0x35, (byte) 0x90, (byte) 0xe6, (byte) 0x81, (byte) 0x3a,
            (byte) 0x26, (byte) 0x4c,
            (byte) 0x3c, (byte) 0x28, (byte) 0x52, (byte) 0xbb, (byte) 0x91, (byte) 0xc3, (byte) 0x00,
            (byte) 0xcb, (byte) 0x88, (byte) 0xd0, (byte) 0x65, (byte) 0x8b, (byte) 0x1b, (byte) 0x53,
            (byte) 0x2e, (byte) 0xa3,
            (byte) 0x71, (byte) 0x64, (byte) 0x48, (byte) 0x97, (byte) 0xa2, (byte) 0x0d, (byte) 0xf9,
            (byte) 0x4e, (byte) 0x38, (byte) 0x19, (byte) 0xef, (byte) 0x46, (byte) 0xa9, (byte) 0xde,
            (byte) 0xac, (byte) 0xd8,
            (byte) 0xa8, (byte) 0xfa, (byte) 0x76, (byte) 0x3f, (byte) 0xe3, (byte) 0x9c, (byte) 0x34,
            (byte) 0x3f, (byte) 0xf9, (byte) 0xdc, (byte) 0xbb, (byte) 0xc7, (byte) 0xc7, (byte) 0x0b,
            (byte) 0x4f, (byte) 0x1d,
            (byte) 0x8a, (byte) 0x51, (byte) 0xe0, (byte) 0x4b, (byte) 0xcd, (byte) 0xb4, (byte) 0x59,
            (byte) 0x31, (byte) 0xc8, (byte) 0x9f, (byte) 0x7e, (byte) 0xc9, (byte) 0xd9, (byte) 0x78,
            (byte) 0x73, (byte) 0x64,
            (byte) 0xea, (byte) 0xc5, (byte) 0xac, (byte) 0x83, (byte) 0x34, (byte) 0xd3, (byte) 0xeb,
            (byte) 0xc3, (byte) 0xc5, (byte) 0x81, (byte) 0xa0, (byte) 0xff, (byte) 0xfa, (byte) 0x13,
            (byte) 0x63, (byte) 0xeb,
            (byte) 0x17, (byte) 0x0d, (byte) 0xdd, (byte) 0x51, (byte) 0xb7, (byte) 0xf0, (byte) 0xda,
            (byte) 0x49, (byte) 0xd3, (byte) 0x16, (byte) 0x55, (byte) 0x26, (byte) 0x29, (byte) 0xd4,
            (byte) 0x68, (byte) 0x9e,
            (byte) 0x2b, (byte) 0x16, (byte) 0xbe, (byte) 0x58, (byte) 0x7d, (byte) 0x47, (byte) 0xa1,
            (byte) 0xfc, (byte) 0x8f, (byte) 0xf8, (byte) 0xb8, (byte) 0xd1, (byte) 0x7a, (byte) 0xd0,
            (byte) 0x31, (byte) 0xce,
            (byte) 0x45, (byte) 0xcb, (byte) 0x3a, (byte) 0x8f, (byte) 0x95, (byte) 0x16, (byte) 0x04,
            (byte) 0x28, (byte) 0xaf, (byte) 0xd7, (byte) 0xfb, (byte) 0xca, (byte) 0xbb, (byte) 0x4b,
            (byte) 0x40, (byte) 0x7e,
            };

    @Override
    public <T> int hash(HashKey<T> hashKey, int seed) {
        int length = hashKey.length();
        long result64;

        if (length <= 16) {
            result64 = hashSmall(hashKey, seed);
        } else if (length <= 240) {
            result64 = hashMedium(hashKey, seed);
        } else {
            result64 = hashLarge(hashKey, seed);
        }

        return toLow32Int(result64);
    }

    private byte[] initSecret(long seed) {
        if (seed == 0L) {
            return DEFAULT_SECRET;
        } else {
            byte[] derivedSecret = new byte[192];
            for (int i = 0; i < 12; i++) {
                putLong64LE(derivedSecret, i * 16, readLong64LE(DEFAULT_SECRET, i * 16) + seed);
                putLong64LE(derivedSecret, i * 16 + 8,
                            readLong64LE(DEFAULT_SECRET, i * 16 + 8) - seed);
            }
            return derivedSecret;
        }
    }

    private static long avalanche(long hash) {
        hash ^= hash >>> 37;
        hash *= PRIME_MX1;
        hash ^= hash >>> 32;
        return hash;
    }

    private static long avalancheXXH64(long hash) {
        hash ^= hash >>> 33;
        hash *= PRIME64_2;
        hash ^= hash >>> 29;
        hash *= PRIME64_3;
        hash ^= hash >>> 32;
        return hash;
    }

    private static long rrmxmx(long hash, int inputLength) {
        hash ^= Long.rotateLeft(hash, 49) ^ Long.rotateLeft(hash, 24);
        hash *= PRIME_MX2;
        hash ^= (hash >>> 35) + inputLength;
        hash *= PRIME_MX2;
        hash ^= hash >>> 28;
        return hash;
    }

    private static long mul128AndFold64(long x, long y) {
        long xLow = x & MASK32;
        long xHigh = x >>> 32;
        long yLow = y & MASK32;
        long yHigh = y >>> 32;

        long lowLow = xLow * yLow;
        long lowHigh = xLow * yHigh;
        long highLow = xHigh * yLow;
        long highHigh = xHigh * yHigh;

        long mid = lowHigh + (highLow & MASK32) + (lowLow >>> 32);
        long hi = highHigh + (highLow >>> 32) + (mid >>> 32);
        long lo = (mid << 32) | (lowLow & MASK32);

        return hi ^ lo;
    }

    private static <T> long mix16(HashKey<T> key, int keyOffset, int secretOffset, long seed) {
        long inputLow = readLong64LEFromInput(key, keyOffset);
        long inputHigh = readLong64LEFromInput(key, keyOffset + 8);
        return mul128AndFold64(inputLow ^ (readLong64LE(DEFAULT_SECRET, secretOffset) + seed),
                               inputHigh ^ (readLong64LE(DEFAULT_SECRET, secretOffset + 8) - seed));
    }

    static int toLow32Int(long value) {
        return (int) value;
    }

    private static <T> int readInt32LE(HashKey<T> key, int offset) {
        return (key.get(offset) & 0xFF) |
               ((key.get(offset + 1) & 0xFF) << 8) |
               ((key.get(offset + 2) & 0xFF) << 16) |
               ((key.get(offset + 3) & 0xFF) << 24);
    }

    private static <T> long readLong64LEFromInput(HashKey<T> key, int offset) {
        return (key.get(offset) & 0xFFL) |
               ((key.get(offset + 1) & 0xFFL) << 8) |
               ((key.get(offset + 2) & 0xFFL) << 16) |
               ((key.get(offset + 3) & 0xFFL) << 24) |
               ((key.get(offset + 4) & 0xFFL) << 32) |
               ((key.get(offset + 5) & 0xFFL) << 40) |
               ((key.get(offset + 6) & 0xFFL) << 48) |
               ((key.get(offset + 7) & 0xFFL) << 56);
    }

    private static int readInt32LE(byte[] input, int offset) {
        if (UnsafeAccess.LITTLE_ENDIAN) {
            return HBasePlatformDependent.getInt(input, offset + BYTE_ARRAY_BASE_OFFSET);
        }
        return Integer.reverseBytes(HBasePlatformDependent.getInt(input, offset + BYTE_ARRAY_BASE_OFFSET));
    }

    private static long readLong64LE(byte[] input, int offset) {
        if (UnsafeAccess.LITTLE_ENDIAN) {
            return HBasePlatformDependent.getLong(input, offset + BYTE_ARRAY_BASE_OFFSET);
        }
        return Long.reverseBytes(HBasePlatformDependent.getLong(input, offset + BYTE_ARRAY_BASE_OFFSET));
    }

    private static void putLong64LE(byte[] input, int offset, long value) {
        if (UnsafeAccess.LITTLE_ENDIAN) {
            HBasePlatformDependent.putLong(input, offset + BYTE_ARRAY_BASE_OFFSET, value);
        } else {
            HBasePlatformDependent.putLong(input, offset + BYTE_ARRAY_BASE_OFFSET, Long.reverseBytes(value));
        }
    }

    @VisibleForTesting
    <T> long hashSmall(HashKey<T> hashKey, long seed) {
        final int length = hashKey.length();
        if (length > 8) {
            long inputFirst = readLong64LEFromInput(hashKey, 0);
            long inputLast = readLong64LEFromInput(hashKey, length - 8);
            long low = ((readLong64LE(DEFAULT_SECRET, 24) ^ readLong64LE(DEFAULT_SECRET, 32)) + seed)
                       ^ inputFirst;
            long high = ((readLong64LE(DEFAULT_SECRET, 40) ^ readLong64LE(DEFAULT_SECRET, 48)) - seed)
                        ^ inputLast;
            long value = length + Long.reverseBytes(low) + high + mul128AndFold64(low, high);
            return avalanche(value);
        } else if (length > 3) {
            // 4-8 bytes of input
            int inputFirst = readInt32LE(hashKey, 0);
            int inputLast = readInt32LE(hashKey, length - 4);
            long combined = toUnsignedLong(inputLast) | (toUnsignedLong(inputFirst) << 32);
            long modifiedSeed = seed ^ (Long.reverseBytes(seed & MASK32));
            long bitflip = (readLong64LE(DEFAULT_SECRET, 8) ^ readLong64LE(DEFAULT_SECRET, 16)) - modifiedSeed;
            long value = combined ^ bitflip;
            return rrmxmx(value, length);
        } else if (length > 0) {
            // 1-3 bytes of input
            int middleOrLast = hashKey.get(length >> 1);
            int first = hashKey.get(0);
            int last = hashKey.get(length - 1);
            long combined = toUnsignedLong((middleOrLast << 24) | (first << 16) | (length << 8) | last);
            long bitflip = toUnsignedLong(readInt32LE(DEFAULT_SECRET, 0) ^ readInt32LE(DEFAULT_SECRET, 4))
                           + seed;
            return avalancheXXH64(bitflip ^ combined);
        }

        // Empty input
        return avalancheXXH64(seed ^ readLong64LE(DEFAULT_SECRET, 56) ^ readLong64LE(DEFAULT_SECRET, 64));
    }

    private <T> long hashLength17To128(HashKey<T> hashKey, long seed) {
        final int length = hashKey.length();
        long acc = length * PRIME64_1;
        switch ((length - 1) / 32) {
            case 3:
                acc += mix16(hashKey, 48, 96, seed);
                acc += mix16(hashKey, length - 64, 112, seed);
            case 2:
                acc += mix16(hashKey, 32, 64, seed);
                acc += mix16(hashKey, length - 48, 80, seed);
            case 1:
                acc += mix16(hashKey, 16, 32, seed);
                acc += mix16(hashKey, length - 32, 48, seed);
            case 0:
                acc += mix16(hashKey, 0, 0, seed);
                acc += mix16(hashKey, length - 16, 16, seed);
                break;
        }

        return avalanche(acc);
    }

    @VisibleForTesting
    <T> long hashMedium(HashKey<T> hashKey, int seed) {
        final int length = hashKey.length();
        long acc = length * PRIME64_1;
        if (length > 128) {
            for (int i = 0; i < 8; i++) {
                acc += mix16(hashKey, i * 16, i * 16, seed);
            }
            acc = avalanche(acc);
            final long numChunks = length >> 4;
            for (int i = 8; i < numChunks; i++) {
                acc += mix16(hashKey, i * 16, (i - 8) * 16 + 3, seed);
            }
            acc += mix16(hashKey, length - 16, 119, seed);
            return avalanche(acc);
        } else if (length > 16) {
            return hashLength17To128(hashKey, seed);
        } else {
            throw new IllegalArgumentException("Length must be bigger than 16 for hashMedium");
        }
    }

    private void accumulate(long[] acc, long[] stripe, byte[] secret, int secretOffset) {
        for (int i = 0; i < 8; i++) {
            long secretWord = readLong64LE(secret, secretOffset + i * 8);
            long value = stripe[i] ^ secretWord;
            acc[i ^ 1] += stripe[i];
            acc[i] += (value & MASK32) * (value >>> 32);
        }
    }

    private <T> void roundAccumulate(long[] acc, HashKey<T> hashKey, byte[] secret, int blockOffset) {
        for (int n = 0; n < 16; n++) {
            long[] stripe = new long[8];
            for (int j = 0; j < 8; j++) {
                stripe[j] = readLong64LEFromInput(hashKey, blockOffset + (n * 64) + (j * 8));
            }
            accumulate(acc, stripe, secret, n * 8);
        }
    }

    <T> long hashLarge(HashKey<T> hashKey, long seed) {
        final int length = hashKey.length();
        long acc0 = PRIME32_3;
        long acc1 = PRIME64_1;
        long acc2 = PRIME64_2;
        long acc3 = PRIME64_3;
        long acc4 = PRIME64_4;
        long acc5 = PRIME32_2;
        long acc6 = PRIME64_5;
        long acc7 = PRIME32_1;

        byte[] secret = initSecret(seed);
        final int blockSize = 1024;
        final int numberOfBlocks = (length - 1) / blockSize;
        final int numberOfStripesPerBlock = (secret.length - 64) / 8;
        for (int b = 0; b < numberOfBlocks; b++) {
            // per block round accumulation
            for (int s = 0; s < numberOfStripesPerBlock; s++) {
                // per stripe
                final int stripeOffset = (b * blockSize) + (s * 64);
                final int secretOffset = s * 8;

                // stripe = 64 bytes
                // 1 stripe = 8 lanes, 1 lane = 8 bytes
                long lane0 = readLong64LEFromInput(hashKey, stripeOffset);
                long secret0 = readLong64LE(secret, secretOffset);
                long value0 = lane0 ^ secret0;
                acc1 += lane0;
                acc0 += (value0 & MASK32) * (value0 >>> 32);

                long lane1 = readLong64LEFromInput(hashKey, stripeOffset + 8);
                long secret1 = readLong64LE(secret, secretOffset + 8);
                long value1 = lane1 ^ secret1;
                acc0 += lane1;
                acc1 += (value1 & MASK32) * (value1 >>> 32);

                long lane2 = readLong64LEFromInput(hashKey, stripeOffset + 16);
                long secret2 = readLong64LE(secret, secretOffset + 16);
                long value2 = lane2 ^ secret2;
                acc3 += lane2;
                acc2 += (value2 & MASK32) * (value2 >>> 32);

                long lane3 = readLong64LEFromInput(hashKey, stripeOffset + 24);
                long secret3 = readLong64LE(secret, secretOffset + 24);
                long value3 = lane3 ^ secret3;
                acc2 += lane3;
                acc3 += (value3 & MASK32) * (value3 >>> 32);

                long lane4 = readLong64LEFromInput(hashKey, stripeOffset + 32);
                long secret4 = readLong64LE(secret, secretOffset + 32);
                long value4 = lane4 ^ secret4;
                acc5 += lane4;
                acc4 += (value4 & MASK32) * (value4 >>> 32);

                long lane5 = readLong64LEFromInput(hashKey, stripeOffset + 40);
                long secret5 = readLong64LE(secret, secretOffset + 40);
                long value5 = lane5 ^ secret5;
                acc4 += lane5;
                acc5 += (value5 & MASK32) * (value5 >>> 32);

                long lane6 = readLong64LEFromInput(hashKey, stripeOffset + 48);
                long secret6 = readLong64LE(secret, secretOffset + 48);
                long value6 = lane6 ^ secret6;
                acc7 += lane6;
                acc6 += (value6 & MASK32) * (value6 >>> 32);

                long lane7 = readLong64LEFromInput(hashKey, stripeOffset + 56);
                long secret7 = readLong64LE(secret, secretOffset + 56);
                long value7 = lane7 ^ secret7;
                acc6 += lane7;
                acc7 += (value7 & MASK32) * (value7 >>> 32);
            }

            // per block scramble
            acc0 = (acc0 ^ (acc0 >>> 47) ^ readLong64LE(secret, (secret.length - 64))) * PRIME32_1;
            acc1 = (acc1 ^ (acc1 >>> 47) ^ readLong64LE(secret, (secret.length - 56))) * PRIME32_1;
            acc2 = (acc2 ^ (acc2 >>> 47) ^ readLong64LE(secret, (secret.length - 48))) * PRIME32_1;
            acc3 = (acc3 ^ (acc3 >>> 47) ^ readLong64LE(secret, (secret.length - 40))) * PRIME32_1;
            acc4 = (acc4 ^ (acc4 >>> 47) ^ readLong64LE(secret, (secret.length - 32))) * PRIME32_1;
            acc5 = (acc5 ^ (acc5 >>> 47) ^ readLong64LE(secret, (secret.length - 24))) * PRIME32_1;
            acc6 = (acc6 ^ (acc6 >>> 47) ^ readLong64LE(secret, (secret.length - 16))) * PRIME32_1;
            acc7 = (acc7 ^ (acc7 >>> 47) ^ readLong64LE(secret, (secret.length - 8))) * PRIME32_1;
        }

        // Last partial block
        final int lastBlockSize = length - (numberOfBlocks * blockSize);
        final int lastBlockOffset = numberOfBlocks * blockSize;
        final int numberOfFullStripes = (lastBlockSize - 1) / 64;
        for (int s = 0; s < numberOfFullStripes; s++) {
            final int stripeOffset = lastBlockOffset + (s * 64);
            final int secretOffset = s * 8;

            long lane0 = readLong64LEFromInput(hashKey, stripeOffset);
            long secret0 = readLong64LE(secret, secretOffset);
            long value0 = lane0 ^ secret0;
            acc1 += lane0;
            acc0 += (value0 & MASK32) * (value0 >>> 32);

            long lane1 = readLong64LEFromInput(hashKey, stripeOffset + 8);
            long secret1 = readLong64LE(secret, secretOffset + 8);
            long value1 = lane1 ^ secret1;
            acc0 += lane1;
            acc1 += (value1 & MASK32) * (value1 >>> 32);

            long lane2 = readLong64LEFromInput(hashKey, stripeOffset + 16);
            long secret2 = readLong64LE(secret, secretOffset + 16);
            long value2 = lane2 ^ secret2;
            acc3 += lane2;
            acc2 += (value2 & MASK32) * (value2 >>> 32);

            long lane3 = readLong64LEFromInput(hashKey, stripeOffset + 24);
            long secret3 = readLong64LE(secret, secretOffset + 24);
            long value3 = lane3 ^ secret3;
            acc2 += lane3;
            acc3 += (value3 & MASK32) * (value3 >>> 32);

            long lane4 = readLong64LEFromInput(hashKey, stripeOffset + 32);
            long secret4 = readLong64LE(secret, secretOffset + 32);
            long value4 = lane4 ^ secret4;
            acc5 += lane4;
            acc4 += (value4 & MASK32) * (value4 >>> 32);

            long lane5 = readLong64LEFromInput(hashKey, stripeOffset + 40);
            long secret5 = readLong64LE(secret, secretOffset + 40);
            long value5 = lane5 ^ secret5;
            acc4 += lane5;
            acc5 += (value5 & MASK32) * (value5 >>> 32);

            long lane6 = readLong64LEFromInput(hashKey, stripeOffset + 48);
            long secret6 = readLong64LE(secret, secretOffset + 48);
            long value6 = lane6 ^ secret6;
            acc7 += lane6;
            acc6 += (value6 & MASK32) * (value6 >>> 32);

            long lane7 = readLong64LEFromInput(hashKey, stripeOffset + 56);
            long secret7 = readLong64LE(secret, secretOffset + 56);
            long value7 = lane7 ^ secret7;
            acc6 += lane7;
            acc7 += (value7 & MASK32) * (value7 >>> 32);
        }

        // handle last stripe
        final int lastStripeOffset = length - 64;
        final int lastSecretOffset = secret.length - 71;

        long lane0 = readLong64LEFromInput(hashKey, lastStripeOffset);
        long secret0 = readLong64LE(secret, lastSecretOffset);
        long value0 = lane0 ^ secret0;
        acc1 += lane0;
        acc0 += (value0 & MASK32) * (value0 >>> 32);

        long lane1 = readLong64LEFromInput(hashKey, lastStripeOffset + 8);
        long secret1 = readLong64LE(secret, lastSecretOffset + 8);
        long value1 = lane1 ^ secret1;
        acc0 += lane1;
        acc1 += (value1 & MASK32) * (value1 >>> 32);

        long lane2 = readLong64LEFromInput(hashKey, lastStripeOffset + 16);
        long secret2 = readLong64LE(secret, lastSecretOffset + 16);
        long value2 = lane2 ^ secret2;
        acc3 += lane2;
        acc2 += (value2 & MASK32) * (value2 >>> 32);

        long lane3 = readLong64LEFromInput(hashKey, lastStripeOffset + 24);
        long secret3 = readLong64LE(secret, lastSecretOffset + 24);
        long value3 = lane3 ^ secret3;
        acc2 += lane3;
        acc3 += (value3 & MASK32) * (value3 >>> 32);

        long lane4 = readLong64LEFromInput(hashKey, lastStripeOffset + 32);
        long secret4 = readLong64LE(secret, lastSecretOffset + 32);
        long value4 = lane4 ^ secret4;
        acc5 += lane4;
        acc4 += (value4 & MASK32) * (value4 >>> 32);

        long lane5 = readLong64LEFromInput(hashKey, lastStripeOffset + 40);
        long secret5 = readLong64LE(secret, lastSecretOffset + 40);
        long value5 = lane5 ^ secret5;
        acc4 += lane5;
        acc5 += (value5 & MASK32) * (value5 >>> 32);

        long lane6 = readLong64LEFromInput(hashKey, lastStripeOffset + 48);
        long secret6 = readLong64LE(secret, lastSecretOffset + 48);
        long value6 = lane6 ^ secret6;
        acc7 += lane6;
        acc6 += (value6 & MASK32) * (value6 >>> 32);

        long lane7 = readLong64LEFromInput(hashKey, lastStripeOffset + 56);
        long secret7 = readLong64LE(secret, lastSecretOffset + 56);
        long value7 = lane7 ^ secret7;
        acc6 += lane7;
        acc7 += (value7 & MASK32) * (value7 >>> 32);

        // final merge
        long result = length * PRIME64_1;
        result += mul128AndFold64(acc0 ^ readLong64LE(secret, 11),
                                  acc1 ^ readLong64LE(secret, 11 + 8));
        result += mul128AndFold64(acc2 ^ readLong64LE(secret, 11 + 16),
                                  acc3 ^ readLong64LE(secret, 11 + 16 + 8));
        result += mul128AndFold64(acc4 ^ readLong64LE(secret, 11 + 32),
                                  acc5 ^ readLong64LE(secret, 11 + 32 + 8));
        result += mul128AndFold64(acc6 ^ readLong64LE(secret, 11 + 48),
                                  acc7 ^ readLong64LE(secret, 11 + 48 + 8));

        return avalanche(result);
    }

    private void roundScramble(long[] acc, byte[] secret) {
        for (int i = 0; i < 8; i++) {
            long secretWord = readLong64LE(secret, (secret.length - 64) + i * 8);
            acc[i] ^= acc[i] >>> 47;
            acc[i] ^= secretWord;
            acc[i] *= PRIME32_1;
        }
    }

    private <T> void round(long[] acc, HashKey<T> hashKey, byte[] secret, int blockOffset) {
        roundAccumulate(acc, hashKey, secret, blockOffset);
        roundScramble(acc, secret);
    }

    private <T> void lastRound(long[] acc, HashKey<T> hashKey, byte[] secret) {

    }

    @VisibleForTesting
    <T> long hashLargeAlloc(HashKey<T> hashKey, int seed) {
        long[] acc = new long[] {
                PRIME32_3, PRIME64_1, PRIME64_2, PRIME64_3,
                PRIME64_4, PRIME32_2, PRIME64_5, PRIME32_1
        };

        byte[] secret = initSecret(seed);

        final int blockSize = 1024;
        int numberOfBlocks = (hashKey.length() - 1) / blockSize;
        for (int i = 0; i < numberOfBlocks; i++) {
            round(acc, hashKey, secret, i * blockSize);
        }

        // Last partial block
        int lastBlockSize = hashKey.length() - (numberOfBlocks * blockSize);
        int lastBlockOffset = numberOfBlocks * blockSize;
        int numberOfFullStripes = (lastBlockSize - 1) / 64;
        for (int i = 0; i < numberOfFullStripes; i++) {
            long[] stripe = new long[8];
            for (int j = 0; j < 8; j++) {
                stripe[j] = readLong64LEFromInput(hashKey, lastBlockOffset + (i * 64) + (j * 8));
            }
            accumulate(acc, stripe, secret, i * 8);
        }

        int lastStripeOffset = hashKey.length() - 64;
        long[] stripe = new long[8];
        for (int i = 0; i < 8; i++) {
            stripe[i] = readLong64LEFromInput(hashKey, lastStripeOffset + (i * 8));
        }
        accumulate(acc, stripe, secret, 192 - 71);

        // final merge
        long result = hashKey.length() * PRIME64_1;
        for (int i = 0; i < 4; i++) {
            result += mul128AndFold64(acc[i * 2] ^ readLong64LE(secret, 11 + i * 16),
                                      acc[i * 2 + 1] ^ readLong64LE(secret, 11 + i * 16 + 8));
        }

        return avalanche(result);
    }
}
