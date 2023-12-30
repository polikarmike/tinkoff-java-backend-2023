package edu.hw9.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSearchTaskTest {
    @Test
    @DisplayName("Проверка поиска файла с расширением txt и размером больше 50")
    void fileSearchTaskTest() throws IOException {
        // Given
        File testDirectory = createTestDirectory("TestDirectory");

        // When
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FileSearchTask fileSearchTask = new FileSearchTask(testDirectory, 50, ".txt");
            List<File> result = forkJoinPool.invoke(fileSearchTask);

            // Then
            assertEquals(1, result.size());
        }
    }

    private File createTestDirectory(String directoryName) throws IOException {
        File testDirectory = Files.createTempDirectory(directoryName).toFile();

        createTestFile(testDirectory, "file1.txt", 60);
        createTestFile(testDirectory, "file2.txt", 40);
        createTestFile(testDirectory, "file3.jpg", 70);

        return testDirectory;
    }

    private void createTestFile(File directory, String fileName, long fileSize) throws IOException {
        File file = new File(directory, fileName);
        Files.createFile(file.toPath());
        byte[] content = new byte[(int) fileSize];
        Files.write(file.toPath(), content);
    }
}
