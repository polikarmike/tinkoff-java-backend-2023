package edu.hw1;

import java.util.Arrays;
import java.util.HashSet;

public class Task6 {

    public static final int FOUR_DIGIT_NUMBER_SIZE = 4;
    public static final int BASE = 10;
    private static final int LEFT_LIMIT = 1000;
    private static final int RIGHT_LIMIT = 9999;
    private static final int KAPREKAR_CONSTANT = 6174;

    private Task6() {
    }

    public static int countK(int n) {
        if (n <= LEFT_LIMIT || n >= RIGHT_LIMIT || areDigitsUnique(n)) {
            throw new IllegalArgumentException(
                "Число должно находится в интервале между 1001 и 9998, и не все цифры должны быть одинаковыми.");
        }
        return recursiveCountK(n, 0);
    }

    private static int recursiveCountK(int n, int steps) {
        if (n == KAPREKAR_CONSTANT) {
            return steps;
        }

        int currNumber = n;
        int[] digits = new int[FOUR_DIGIT_NUMBER_SIZE];
        for (int i = FOUR_DIGIT_NUMBER_SIZE - 1; i >= 0; i--) {
            digits[i] = currNumber % BASE;
            currNumber /= BASE;
        }

        Arrays.sort(digits);

        int ascending = 0;
        int descending = 0;

        for (int i = 0; i < FOUR_DIGIT_NUMBER_SIZE; i++) {
            ascending += digits[i] * Math.pow(BASE, FOUR_DIGIT_NUMBER_SIZE - i - 1);
            descending += digits[i] * Math.pow(BASE, i);
        }

        return recursiveCountK(descending - ascending, steps + 1);
    }

    public static boolean areDigitsUnique(int number) {
        String numberStr = Integer.toString(number);
        HashSet<Character> digitSet = new HashSet<>();

        for (int i = 0; i < numberStr.length(); i++) {
            char digit = numberStr.charAt(i);
            digitSet.add(digit);
        }
        return !(digitSet.size() > 1);
    }

}
