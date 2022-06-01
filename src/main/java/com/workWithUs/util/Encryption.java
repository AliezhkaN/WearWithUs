package com.workWithUs.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encryption - class to encrypt a password
 *
 * @author Oleh Nahorniak.
 */
public final class Encryption {

    public static String md5(String input) {
        StringBuilder sb = new StringBuilder("");
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(input.getBytes());
            for (byte b : bytes) {
                sb.append(String.format("%02X", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
