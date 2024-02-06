package com.github.vsandeev.consistentHash;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashFunction implements HashFunction {

    /**
     * Converts String input into long MD5 hash
     */
    public long hash(String key) {
            // Create MD5 hash instance
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // Update the message digest with the input string
        md.update(key.getBytes());

        // Generate the MD5 hash
        byte[] digest = md.digest();

        // Convert the first 8 bytes of the hash to a long value
        return  bytesToLong(digest);
   }


    // Convert byte array to long
    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes, 0, Long.BYTES);
        buffer.flip();
        return buffer.getLong();
    }
}
