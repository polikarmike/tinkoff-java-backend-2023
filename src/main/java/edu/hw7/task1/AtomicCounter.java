package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void incrementCounter(int times, int numThreads) {
        Thread[] threads = new Thread[numThreads];
        int incrementsPerThread = times / numThreads;
        int remainingIncrements = times % numThreads;

        for (int i = 0; i < numThreads; i++) {
            int threadIncrements = incrementsPerThread + (i < remainingIncrements ? 1 : 0);
            threads[i] = new Thread(() -> increment(threadIncrements));
        }

        startAndJoinThreads(threads);
    }

    public int getCounterValue() {
        return counter.get();
    }

    private void increment(int times) {
        for (int i = 0; i < times; i++) {
            counter.incrementAndGet();
        }
    }

    private void startAndJoinThreads(Thread... threads) {
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
