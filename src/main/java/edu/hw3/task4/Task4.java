package edu.hw3.task4;

public class Task4 {
    private Task4() {}

    private static final String[] ROMAN_SYMBOLS =
        {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    private static final int[] ARABIC_VALUES =
        {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    private static final int MAX_ROMAN_NUMBER = 3999;
    private static final int ARABIC_VALUES_LENGTH = ARABIC_VALUES.length - 1;

    public static String convertToRoman(int number) {
        if (number < 1 || number > MAX_ROMAN_NUMBER) {
            return "Недопустимое число";
        }

        StringBuilder romanNumber = new StringBuilder();
        int i = ARABIC_VALUES_LENGTH;
        int remainingNumber = number;

        while (remainingNumber > 0) {
            if (remainingNumber >= ARABIC_VALUES[i]) {
                romanNumber.append(ROMAN_SYMBOLS[i]);
                remainingNumber -= ARABIC_VALUES[i];
            } else {
                i--;
            }
        }

        return romanNumber.toString();
    }
}
