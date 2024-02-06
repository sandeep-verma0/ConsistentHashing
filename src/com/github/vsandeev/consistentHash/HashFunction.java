package com.github.vsandeev.consistentHash;


import java.security.NoSuchAlgorithmException;

public interface HashFunction {

    /**
     * Converts String input into long  hash
     */
   long hash(String key);
}
