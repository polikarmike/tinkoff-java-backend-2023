package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedPasswordCracker {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MAX_LENGTH_WORD = 6;
    private static final int HEX_255 = 0xff;


    private MultiThreadedPasswordCracker() {}

    public static Map<String, String> decode(Map<String, String> passwordHashes, int numThreads)
        throws NoSuchAlgorithmException, InterruptedException {
        Map<String, String> crackedPasswords = new HashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int length = 1; length <= MAX_LENGTH_WORD && !passwordHashes.isEmpty(); length++) {
            int finalLength = length;
            executor.submit(() -> {
                try {
                    generatePasswords("", finalLength, passwordHashes, crackedPasswords);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        return crackedPasswords;
    }

    private static synchronized boolean generatePasswords(String current,
        int length, Map<String, String> passwordHashes,
        Map<String, String> crackedPasswords) throws NoSuchAlgorithmException {
        if (length == 0) {
            return checkPassword(current, passwordHashes, crackedPasswords);
        } else {
            for (int i = 0; i < ALPHABET.length(); i++) {
                if (generatePasswords(current + ALPHABET.charAt(i),
                    length - 1, passwordHashes, crackedPasswords)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static synchronized boolean checkPassword(String password, Map<String, String> passwordHashes,
        Map<String, String> crackedPasswords) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & HEX_255));
        }
        String hash = sb.toString();

        Iterator<Map.Entry<String, String>> iterator = passwordHashes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getValue().equals(hash)) {
                crackedPasswords.put(entry.getKey(), password);
                iterator.remove();
            }
        }
        return passwordHashes.isEmpty();
    }
}
