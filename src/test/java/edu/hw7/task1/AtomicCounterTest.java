package edu.hw7.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtomicCounterTest {

    @Test
    @DisplayName("Тест увеличения счетчика")
    public void testIncrementCounter() {
        // Given
        AtomicCounter atomicCounter = new AtomicCounter();

        // When
        atomicCounter.incrementCounter(1000, 5);

        // Then
        assertEquals(1000, atomicCounter.getCounterValue());
    }

    @Test
    @DisplayName("Тест нескольких вызовов incrementCounter")
    public void testMultipleIncrementCounterCalls() {
        // Given
        AtomicCounter atomicCounter = new AtomicCounter();

        // When
        atomicCounter.incrementCounter(500, 3);
        atomicCounter.incrementCounter(200, 2);

        // Then
        assertEquals(700, atomicCounter.getCounterValue());
    }

    @Test
    @DisplayName("Тест увеличения счетчика с использованием одного потока")
    public void testIncrementCounterWithSingleThread() {
        // Given
        AtomicCounter atomicCounter = new AtomicCounter();

        // When
        atomicCounter.incrementCounter(100, 1);

        // Then
        assertEquals(100, atomicCounter.getCounterValue());
    }

    @Test
    @DisplayName("Тест увеличения счетчика с большим числом")
    public void testLargeIncrementCounter() {
        // Given
        AtomicCounter atomicCounter = new AtomicCounter();

        // When
        atomicCounter.incrementCounter(1000000, 10);

        // Then
        assertEquals(1000000, atomicCounter.getCounterValue());
    }
}


