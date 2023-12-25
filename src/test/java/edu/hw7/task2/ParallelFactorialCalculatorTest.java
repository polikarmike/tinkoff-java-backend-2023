package edu.hw7.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParallelFactorialCalculatorTest {

    @Test
    @DisplayName("Вычисление факториала для 5")
    public void testFactorialFor5() {
        // Given
        int n = 5;

        // When
        long result = ParallelFactorialCalculator.factorial(n);

        // Then
        assertEquals(120, result);
    }

    @Test
    @DisplayName("Вычисление факториала для 0")
    public void testFactorialFor0() {
        // Given
        int n = 0;

        // When
        long result = ParallelFactorialCalculator.factorial(n);

        // Then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Вычисление факториала для 1")
    public void testFactorialFor1() {
        // Given
        int n = 1;

        // When
        long result = ParallelFactorialCalculator.factorial(n);

        // Then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Вычисление факториала для 10")
    public void testFactorialFor10() {
        // Given
        int n = 10;

        // When
        long result = ParallelFactorialCalculator.factorial(n);

        // Then
        assertEquals(3628800, result);
    }

    @Test
    @DisplayName("Попытка вычислить факториал для отрицательного числа")
    public void testFactorialForNegativeNumber() {
        // Given
        int negativeNumber = -5;

        // When, Then
        assertThrows(IllegalArgumentException.class, () ->
            ParallelFactorialCalculator.factorial(negativeNumber)
        );
    }
}
