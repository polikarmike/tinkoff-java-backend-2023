package edu.hw7.task2;

import java.util.stream.LongStream;

public class ParallelFactorialCalculator {

    private ParallelFactorialCalculator() {

    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал не определен для отрицательных чисел");
        }
        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}
