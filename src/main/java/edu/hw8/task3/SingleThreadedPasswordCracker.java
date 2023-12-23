package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SingleThreadedPasswordCracker {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MAX_LENGTH_WORD = 6;
    private static final int HEX_255 = 0xff;

    private SingleThreadedPasswordCracker() {}

    public static Map<String, String> decode(Map<String, String> passwordHashes)
        throws NoSuchAlgorithmException {
        Map<String, String> crackedPasswords = new HashMap<>();

        for (int length = 1; length <= MAX_LENGTH_WORD && !passwordHashes.isEmpty(); length++) {
            generatePasswords(length, passwordHashes, crackedPasswords);
        }

        return crackedPasswords;
    }

    private static void generatePasswords(int length, Map<String, String> passwordHashes,
        Map<String, String> crackedPasswords)
        throws NoSuchAlgorithmException {

        for (int i = 0; i < Math.pow(ALPHABET.length(), length); i++) {
            String password = generatePassword(i, length);
            if (checkPassword(password, passwordHashes, crackedPasswords)) {
                break;
            }
        }
    }

    private static String generatePassword(int index, int length) {
        int tempIndex = index;

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int charIndex = tempIndex % ALPHABET.length();
            password.append(ALPHABET.charAt(charIndex));
            tempIndex /= ALPHABET.length();
        }
        return password.toString();
    }

    private static boolean checkPassword(String password, Map<String, String> passwordHashes,
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
