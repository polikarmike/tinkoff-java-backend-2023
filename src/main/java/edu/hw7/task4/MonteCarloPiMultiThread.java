package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarloPiMultiThread {
    private static final double PI_APPROXIMATION_CONSTANT = 4.0;

    private MonteCarloPiMultiThread() {

    }

    public static double calculatePiMultiThread(int iterations, int numThreads) {
        AtomicInteger circleCount = new AtomicInteger();
        int iterationsPerThread = iterations / numThreads;

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> runSimulation(iterationsPerThread, circleCount));
        }

        startAndJoinThreads(threads);

        return PI_APPROXIMATION_CONSTANT * circleCount.get() / iterations;
    }

    private static void runSimulation(int iterations, AtomicInteger circleCount) {
        int localCircleCount = 0;
        for (int j = 0; j < iterations; j++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            double distance = x * x + y * y;

            if (distance <= 1.0) {
                localCircleCount++;
            }
        }
        circleCount.addAndGet(localCircleCount);
    }

    private static void startAndJoinThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Ошибка при объединении потоков", e);
            }
        }
    }

}

