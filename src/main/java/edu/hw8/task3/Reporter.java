package edu.hw8.task3;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class Reporter {
    private static final String PASSWORD_CRACKER_HEADER = "Отчет\n";
    private static final String THREADS_RESULT_FORMAT = "Потоки: %d, Время: %.6f s, Ускорение: %.6f\n";
    private static final double TIME_CONVERSION_CONSTANT = 1e9;
    private static final String AVERAGE_SPEEDUP_FORMAT = "Среднее ускорение: %.6f\n\n";

    private Reporter() {

    }

    private static void simulateAndWriteToFile(String filename, Map<String, String> passwordHashes,
        int numThreads) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(PASSWORD_CRACKER_HEADER);
            double totalSpeedupTime = 0.0;

            double sequentialTime = getSingleThreadedPasswordCrackerTime(passwordHashes);


            for (int i = 1; i <= numThreads; i++) {
                long startTime = System.nanoTime();
                MultiThreadedPasswordCracker.decode(passwordHashes, i);
                long endTime = System.nanoTime();
                double elapsedTime = (endTime - startTime);

                double speedupTime = (double) sequentialTime / elapsedTime;
                totalSpeedupTime += speedupTime;

                String line = String.format(
                    THREADS_RESULT_FORMAT,
                    i,
                    elapsedTime / TIME_CONVERSION_CONSTANT,
                    speedupTime
                );

                writer.write(line);
            }

            writer.write("\n");
            double averageSpeedup = totalSpeedupTime / numThreads;
            writer.write(String.format(AVERAGE_SPEEDUP_FORMAT, averageSpeedup));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static double getSingleThreadedPasswordCrackerTime(Map<String, String> passwordHashes)
        throws NoSuchAlgorithmException {
        long startTime = System.nanoTime();
        SingleThreadedPasswordCracker.decode(passwordHashes);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static Map<String, String> createPasswordHashes() {
        Map<String, String> passwordHashes = new HashMap<>();
        passwordHashes.put("a.v.petrov", "c47d187067c6cf953245f128b5fde62a");
        passwordHashes.put("v.v.belov", "81dc9bdb52d04dc20036dbd8313ed055");
        return passwordHashes;
    }

    public static void createReport(String filePath, Map<String, String> passwordHashes, int numThreads) {
        try {
            simulateAndWriteToFile(filePath, passwordHashes, numThreads);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
