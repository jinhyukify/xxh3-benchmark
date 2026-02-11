package hash;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class XXH3Test {
    private final XXH3 xxh3 = new XXH3();

    @Test
    public void test_1_to_3_hash() {
        final byte[] data = new byte[] {0};
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 1);
        long hash = xxh3.hashSmall(key, key.length(), 0);
        assertEquals(-4302098779834749733L, hash);
        hash = xxh3.hashSmall(key, key.length(), -123);
        assertEquals(-4335726737540037703L, hash);
        final byte[] data2 = new byte[] {1, 2, 3};
        final ByteArrayHashKey key2 = new ByteArrayHashKey(data2, 0, 3);
        hash = xxh3.hashSmall(key2, key2.length(), -123);
        assertEquals(-8844228299136966938L, hash);
    }

    @Test
    public void test_4_to_8_hash() {
        final byte[] data = new byte[] { 0, 1, 2, 3 };
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 4);
        final int length = 4;
        long hash = xxh3.hashSmall(key, length, 0);
        assertEquals(6979084321315492338L, hash);
        hash = xxh3.hashSmall(key, length, -123);
        assertEquals(-6733462606704881490L, hash);
        final byte[] data2 = new byte[] { 0, 1, 2, 3, 4, 5 };
        final ByteArrayHashKey key2 = new ByteArrayHashKey(data2, 0, 6);
        hash = xxh3.hashSmall(key2, key2.length(), -123);
        assertEquals(7032850356126904070L, hash);
    }

    @Test
    public void test_9_to_16_hash() {
        final byte[] data = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 9);
        final int length = 9;
        long hash = xxh3.hashSmall(key, length, 0);
        assertEquals(-1629980255024596516L, hash);
        hash = xxh3.hashSmall(key, length, -123);
        assertEquals(-7297801414810353441L, hash);
    }


    @Test
    public void test_17_to_128_hash() {
        final byte[] data = new byte[20];
        for (int i = 0; i < 20; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 20);
        final int length = 20;
        long hash = xxh3.hashMedium(key, length, 0);
        assertEquals(2917394212576059053L, hash);
        hash = xxh3.hashMedium(key, length, -123);
        assertEquals(-7683864932334533431L, hash);
    }

    @Test
    public void test_128_hash() {
        final byte[] data = new byte[128];
        for (int i = 0; i < 128; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0,128);
        final int length = 128;
        long hash = xxh3.hashMedium(key, length, 0);
        assertEquals(-8807326403944725397L, hash);
        hash = xxh3.hashMedium(key, length, -123);
        assertEquals(8741294656117520052L, hash);
    }

    @Test
    public void test_129_to_256_hash() {
        final byte[] data = new byte[200];
        for (int i = 0; i < 200; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 200);
        final int length = 200;
        long hash = xxh3.hashMedium(key, length, 0);
        assertEquals(-852719212082297085L, hash);
        hash = xxh3.hashMedium(key, length, -123);
        assertEquals(7827692793159299477L, hash);
    }

    @Test
    public void test_257_plus_hash() {
        final byte[] data = new byte[241];
        for (int i = 0; i < 241; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 241);
        final int length = 241;
        long hash = xxh3.hashLarge(key, length, 0);
        assertEquals(209643423615708418L, hash);
        hash = xxh3.hashLarge(key, length, -123);
        assertEquals(-8678730915841767784L, hash);
    }

    @Test
    public void test_500_hash() {
        final byte[] data = new byte[500];
        for (int i = 0; i < 500; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 500);
        final int length = 500;
        long hash = xxh3.hashLarge(key, length, 0);
        assertEquals(-4526166123204455007L, hash);
    }

    @Test
    public void test_1024_hash() {
        final byte[] data = new byte[1024];
        for (int i = 0; i < 1024; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 1024);
        final int length = 1024;
        long hash = xxh3.hashLarge(key, length, 0);
        assertEquals(-6309269121238725342L, hash);
    }

    @Test
    public void test_1523_hash() {
        final byte[] data = new byte[1523];
        for (int i = 0; i < 1523; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 1523);
        final int length = 1523;
        long hash = xxh3.hashLarge(key, length, 0);
        assertEquals(-8549957081212159598L, hash);
    }

    @Test
    public void test_12345_hash() {
        final byte[] data = new byte[12345];
        for (int i = 0; i < 12345; i++) {
            data[i] = (byte) i;
        }
        final ByteArrayHashKey key = new ByteArrayHashKey(data, 0, 12345);
        final int length = 12345;
        long hash = xxh3.hashLarge(key, length, 0);
        assertEquals(5522732798117088770L, hash);
    }
}
