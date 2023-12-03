package edu.hw8.task2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;

public class FixedThreadPoolTest {
    private FixedThreadPool threadPool;

    @Before
    public void givenFixedThreadPool() {
        threadPool = new FixedThreadPool(4);
        threadPool.start();
    }

    @Test
    @DisplayName("Проверка работоспособность на примере параллельного вычисления чисел Фибоначчи")
    public void testFibonacci() throws InterruptedException {
        // Given
        final int[] fibonacciResults = new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        CountDownLatch latch = new CountDownLatch(10);

        // When & Then
        for (int i = 0; i <= 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                long result = fibonacci(finalI);
                System.out.println("Fibonacci of " + finalI + " is " + result);
                assertEquals(fibonacciResults[finalI], result);
                latch.countDown();
            });
        }
    }

    @After
    public void thenCloseThreadPool() {
        threadPool.close();
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
