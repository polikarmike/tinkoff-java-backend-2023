package edu.hw1;

public class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        int number = n;
        int sign = 1;
        if (number < 0) {
            sign = -1;
            number *= sign;
        }

        int numBits = Integer.SIZE - Integer.numberOfLeadingZeros(number);
        int mask = (1 << numBits) - 1;
        return sign * (((number << shift) | (number >>> (numBits - shift))) & mask);
    }

    public static int rotateRight(int n, int shift) {
        int number = n;
        int sign = 1;
        if (number < 0) {
            sign = -1;
            number *= sign;
        }

        int numBits = Integer.SIZE - Integer.numberOfLeadingZeros(number);
        int mask = (1 << numBits) - 1;
        return sign * (((number >>> shift) | (number << (numBits - shift))) & mask);
    }
}

