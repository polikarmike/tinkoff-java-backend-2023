package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length - 1; i += 2) {
            char temp = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = temp;
        }

        return new String(charArray);
    }
}
