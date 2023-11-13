package edu.hw3.task1;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (isLatinLetter(c)) {
                char start = Character.isUpperCase(c) ? 'A' : 'a';
                char end = Character.isUpperCase(c) ? 'Z' : 'z';
                char mirroredChar = (char) (end - (c - start));
                result.append(mirroredChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static boolean isLatinLetter(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
    }
}
