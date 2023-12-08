package edu.hw7.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MonteCarloPiTest {
    @Test
    @DisplayName("Проверка вычисления Pi в одном потоке")
    void testCalculatePiSingleThread() {
        // Given
        int iterations = 1_000_000;

        // When
        double pi = MonteCarloPiSingleThread.calculatePiSingleThread(iterations);

        // Then
        double epsilon = 0.01;
        assertTrue(pi > 3.14159 - epsilon && pi < 3.14159 + epsilon);
    }

    @Test
    @DisplayName("Проверка вычисления Pi с использованием нескольких потоков")
    void testCalculatePiMultiThread() {
        // Given
        int iterations = 1_000_000;
        int numThreads = 4;

        // When
        double pi = MonteCarloPiMultiThread.calculatePiMultiThread(iterations, numThreads);

        // Then
        double epsilon = 0.01;
        assertTrue(pi > 3.14159 - epsilon && pi < 3.14159 + epsilon);
    }

    @Test
    @DisplayName("Проверка создания отчета")
    void testCreateReport() {
        // Given
        String filePath = "src/test/java/edu/hw7/task4/test_github_simulation_results.txt";
        List<Integer> iterationsList = List.of(1_000_000, 500_000, 250_000);
        int numThreads = 4;

        // When
        Reporter.createReport(filePath, iterationsList, numThreads);

        // Then
        assertTrue(new java.io.File(filePath).exists());
    }

}
