package edu.hw8.task2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedThreadPoolTest {
    private long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Test
    @DisplayName("Проверка работоспособность на примере параллельного вычисления чисел Фибоначчи")
    public void testFibonacci()  throws InterruptedException {
        int numThreads = 5;
        int numTasks = 10;
        final int[] fibonacciResults = new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        AtomicInteger counter = new AtomicInteger();

        FixedThreadPool threadPool = new FixedThreadPool(numThreads);
        threadPool.start();

        for (int i = 0; i < numTasks; i++) {
            int taskNum = i;
            threadPool.execute(() -> {
                long result = fibonacci(taskNum);
                assertEquals(fibonacciResults[taskNum], result);
                counter.incrementAndGet();
            });
        }

        Thread.sleep(100);
        assertEquals(numTasks, counter.get());


    }
}
