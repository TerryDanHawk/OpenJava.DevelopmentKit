package com.openjava.mvc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    /**
     * a b c d e f 也可以改成大写的 A B C D E F
     */
    private static final char[] CHAR_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String generateMD5(String str) {
        if (str == null) {
            throw new NullPointerException("Parameters is null");
        }
        if ("".equals(str)) {
            throw new RuntimeException("Empty string");
        }
        MessageDigest msgDigest;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm exception");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported encoding exception");
        }
        byte[] bytes = msgDigest.digest();
        char[] out = new char[16 * 2];
        int len = 16;
        for (int i = 0, j = 0; i < len; i++) {
            out[j++] = CHAR_ARRAY[bytes[i] >>> 4 & 0xf];
            out[j++] = CHAR_ARRAY[bytes[i] & 0xf];
        }
        return new String(out);
    }
}
