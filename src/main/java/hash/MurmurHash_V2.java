package hash;

public class MurmurHash_V2 extends Hash {
    @Override
    public <T> int hash(HashKey<T> hashKey, int seed) {
        int m = 0x5bd1e995;
        int r = 24;
        int length = hashKey.length();
        int h = seed ^ length;

        int len_4 = length >> 2;

        for (int i = 0; i < len_4; i++) {
            int k = hashKey.getIntLE(i << 2);
            k *= m;
            k ^= k >>> r;
            k *= m;
            h *= m;
            h ^= k;
        }

        // avoid calculating modulo
        int len_m = len_4 << 2;
        int left = length - len_m;
        int i_m = len_m;

        if (left != 0) {
            if (left >= 3) {
                h ^= hashKey.get(i_m + 2) << 16;
            }
            if (left >= 2) {
                h ^= hashKey.get(i_m + 1) << 8;
            }
            if (left >= 1) {
                h ^= hashKey.get(i_m);
            }

            h *= m;
        }

        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;

        return h;
    }
}
