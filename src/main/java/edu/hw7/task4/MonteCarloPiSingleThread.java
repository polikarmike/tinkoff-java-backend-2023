package edu.hw7.task4;

import java.util.Random;

public class MonteCarloPiSingleThread {
    private static final double PI_APPROXIMATION_CONSTANT = 4.0;

    private MonteCarloPiSingleThread() {

    }

    public static double calculatePiSingleThread(int iterations) {
    long circleCount = 0;
    Random random = new Random();
    for (int j = 0; j < iterations; j++) {
        double x = random.nextDouble();
        double y = random.nextDouble();
        double distance = x * x + y * y;
            if (distance <= 1.0) {
                circleCount++;
            }
    }
    return PI_APPROXIMATION_CONSTANT * circleCount / iterations;
    }
}
