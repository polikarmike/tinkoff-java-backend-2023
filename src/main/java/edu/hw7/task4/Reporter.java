package edu.hw7.task4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static edu.hw7.task4.MonteCarloPiMultiThread.calculatePiMultiThread;
import static edu.hw7.task4.MonteCarloPiSingleThread.calculatePiSingleThread;

public class Reporter {
    private static final String ITERATIONS_HEADER = "Количество итераци: %d\n";
    private static final String THREADS_RESULT_FORMAT =
        "Потоки: %d, Результат: %.6f, Погрешность: %.6f, Время: %.6f s, Ускорение: %.6f\n";
    private static final String AVERAGE_SPEEDUP_FORMAT = "Среднее ускорение: %.6f\n\n";
    private static final double PERCENTAGE_CONVERSION_CONSTANT = 100.0;
    private static final double TIME_CONVERSION_CONSTANT = 1e9;

    private Reporter() {

    }

    private static void simulateAndWriteToFile(String filename, int iterations, int numThreads) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(String.format(ITERATIONS_HEADER, iterations));
            double totalSpeedupTime = 0.0;

            double sequentialTime = getTimeCalculatePiSingleThread(iterations);

            for (int numThread = 1; numThread <= numThreads; numThread++) {
                long startTime = System.nanoTime();
                double piApproximation = calculatePiMultiThread(iterations, numThread);
                long endTime = System.nanoTime();

                double deviation = Math.abs((Math.PI - piApproximation) / Math.PI) * PERCENTAGE_CONVERSION_CONSTANT;
                long parallelTime = endTime - startTime;

                double speedupTime = (double) sequentialTime / parallelTime;
                totalSpeedupTime += speedupTime;

                String line = String.format(
                    THREADS_RESULT_FORMAT,
                    numThread,
                    piApproximation,
                    deviation,
                    parallelTime / TIME_CONVERSION_CONSTANT,
                    speedupTime
                );
                writer.write(line);
            }
            double averageSpeedup = totalSpeedupTime / numThreads;
            writer.write(String.format(AVERAGE_SPEEDUP_FORMAT, averageSpeedup));

        }
    }

    private static double getTimeCalculatePiSingleThread(int iterations) {
        long startTime = System.nanoTime();
        calculatePiSingleThread(iterations);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void createReport(String filePath, List<Integer> iterationsList, int numThreads) {
        try {
            for (int iterations : iterationsList) {
                simulateAndWriteToFile(filePath, iterations, numThreads);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании отчета", e);
        }
    }
}
