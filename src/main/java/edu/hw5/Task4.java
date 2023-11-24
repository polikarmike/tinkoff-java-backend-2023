package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    private Task4() {}

    private static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile(".*[~!@#$%^&*|].*");

    public static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
        return SPECIAL_CHARACTER_PATTERN.matcher(password).matches();
    }
}
