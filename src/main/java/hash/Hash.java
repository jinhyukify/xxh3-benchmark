package hash;

public abstract class Hash {
    /** Constant to denote invalid hash type. */
    public static final int INVALID_HASH = -1;
    /** Constant to denote {@link JenkinsHash}. */
    public static final int JENKINS_HASH = 0;
    /** Constant to denote {@link MurmurHash}. */
    public static final int MURMUR_HASH = 1;
    /** Constant to denote {@link MurmurHash3}. */
    public static final int MURMUR_HASH3 = 2;
    /** Constant to denote {@link SlowPathXXH3}. */
    public static final int XXH3_HASH = 3;

    /**
     * This utility method converts String representation of hash function name to a symbolic
     * constant. Currently four function types are supported, "jenkins", "murmur", "murmur3" and "xxh3".
     * @param name hash function name
     * @return one of the predefined constants
     */
    public static int parseHashType(String name) {
        if ("jenkins".equalsIgnoreCase(name)) {
            return JENKINS_HASH;
        } else if ("murmur".equalsIgnoreCase(name)) {
            return MURMUR_HASH;
        } else if ("murmur3".equalsIgnoreCase(name)) {
            return MURMUR_HASH3;
        } else if ("xxh3".equalsIgnoreCase(name)) {
            return XXH3_HASH;
        } else {
            return INVALID_HASH;
        }
    }

    /**
     * Get a singleton instance of hash function of a given type.
     * @param type predefined hash type
     * @return hash function instance, or null if type is invalid
     */
    public static Hash getInstance(int type) {
        switch (type) {
            case JENKINS_HASH:
                return JenkinsHash.getInstance();
            case MURMUR_HASH:
                return MurmurHash.getInstance();
            case MURMUR_HASH3:
                return MurmurHash3.getInstance();
            case XXH3_HASH:
                return SlowPathXXH3.getInstance();
            default:
                return null;
        }
    }

    /**
     * Calculate a hash using bytes from HashKey and the provided seed value.
     * @param hashKey key to extract the hash
     * @param initval the seed value
     * @return hash value
     */
    public abstract <T> int hash(HashKey<T> hashKey, int initval);
}

