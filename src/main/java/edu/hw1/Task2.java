package edu.hw1;

public class Task2 {
    public static final int BASE = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        int count = 0;

        if (number == 0) {
            return 1;
        }

        int digits = number;

        while (digits != 0) {
            digits /= BASE;
            count++;
        }

        return count;
    }
}
