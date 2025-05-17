package com.jvictornascimento.urtshortener.services.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int HASH_SIZE = 8;

    public static String GeneratorShortHash(String input){
        try {
            //generates a sha-256 from the input
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes());

            //converts into a BigInteger
            BigInteger bigInteger = new BigInteger(1, digest);

            //encode in base62
            StringBuilder base62hash = new StringBuilder();
            while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
                int idx = bigInteger.mod(BigInteger.valueOf(62)).intValue();
                base62hash.append(BASE62.charAt(idx));
                bigInteger = bigInteger.divide(BigInteger.valueOf(62));
            }

            //return first size characters
            return base62hash.reverse().substring(0, Math.min(HASH_SIZE, base62hash.length()));

        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error generating short hash!");
        }
    }
}
