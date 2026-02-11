package hash;

public class MurmurHash3 extends Hash {
    private static MurmurHash3 _instance = new MurmurHash3();

    public static Hash getInstance() {
        return _instance;
    }

    /** Returns the MurmurHash3_x86_32 hash. */
    @Override
    public <T> int hash(HashKey<T> hashKey, int initval) {
        final int c1 = 0xcc9e2d51;
        final int c2 = 0x1b873593;
        int length = hashKey.length();
        int h1 = initval;
        int roundedEnd = (length & 0xfffffffc); // round down to 4 byte block

        for (int i = 0; i < roundedEnd; i += 4) {
            // little endian load order
            int k1 = (hashKey.get(i) & 0xff) | ((hashKey.get(i + 1) & 0xff) << 8)
                     | ((hashKey.get(i + 2) & 0xff) << 16) | (hashKey.get(i + 3) << 24);
            k1 *= c1;
            k1 = (k1 << 15) | (k1 >>> 17); // ROTL32(k1,15);
            k1 *= c2;

            h1 ^= k1;
            h1 = (h1 << 13) | (h1 >>> 19); // ROTL32(h1,13);
            h1 = h1 * 5 + 0xe6546b64;
        }

        // tail
        int k1 = 0;

        switch (length & 0x03) {
            case 3:
                k1 = (hashKey.get(roundedEnd + 2) & 0xff) << 16;
                // FindBugs SF_SWITCH_FALLTHROUGH
            case 2:
                k1 |= (hashKey.get(roundedEnd + 1) & 0xff) << 8;
                // FindBugs SF_SWITCH_FALLTHROUGH
            case 1:
                k1 |= (hashKey.get(roundedEnd) & 0xff);
                k1 *= c1;
                k1 = (k1 << 15) | (k1 >>> 17); // ROTL32(k1,15);
                k1 *= c2;
                h1 ^= k1;
            default:
                // fall out
        }

        // finalization
        h1 ^= length;

        // fmix(h1);
        h1 ^= h1 >>> 16;
        h1 *= 0x85ebca6b;
        h1 ^= h1 >>> 13;
        h1 *= 0xc2b2ae35;
        h1 ^= h1 >>> 16;

        return h1;
    }
}
