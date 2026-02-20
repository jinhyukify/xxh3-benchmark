package hash;

import java.nio.ByteOrder;

import net.openhft.hashing.Access;

public interface AccessFactory {
   Access<HashKey<?>> HASHKEY_ACCESS = new Access<>() {
       @Override
       public long getLong(HashKey input, long offset) {
           return input.getLongLE((int) offset);
       }

       @Override
       public int getInt(HashKey input, long offset) {
           return input.getIntLE((int) offset);
       }

       @Override
       public int getByte(HashKey input, long offset) {
           return input.get((int) offset);
       }

       @Override
       public ByteOrder byteOrder(HashKey input) {
           return ByteOrder.LITTLE_ENDIAN;
       }

       @Override
       protected Access<HashKey<?>> reverseAccess() {
           return null;
       }
   };
}
