package edu.hw1;

import java.util.regex.Pattern;

public class Task1 {
    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static long minutesToSeconds(String input) {
        if (!isValidTimeFormat(input)) {
            return -1;
        }

        String[] parts = input.split(":");

        long minutes = Long.parseLong(parts[0]);
        long seconds = Long.parseLong(parts[1]);

        return minutes * SECONDS_IN_MINUTE + seconds;
    }

    private static boolean isValidTimeFormat(String timeString) {
        String pattern = "^[0-9]{1,9}:[0-5][0-9]$";

        if (timeString == null) {
            return false;
        }

        return Pattern.matches(pattern, timeString);
    }
}
