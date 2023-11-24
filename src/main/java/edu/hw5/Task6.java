package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    private Task6() {}

    public static boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Обе строки должны быть непустыми");
        }

        StringBuilder regex = new StringBuilder(".*");
        for (char c : s.toCharArray()) {
            regex.append(c).append(".*");
        }
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(t);
        return matcher.matches();
    }
}
