package edu.hw9.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectorySearchTaskTest {
    @Test
    @DisplayName("Проверка поиска папки с 1001 файлом")
    void directorySearchTaskTest(@TempDir Path tempDir) throws IOException {
        // Given
        File testDirectory = createTestDirectory("TestDirectory", 1001);

        // When
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DirectorySearchTask directorySearchTask = new DirectorySearchTask(testDirectory);
            List<File> result = forkJoinPool.invoke(directorySearchTask);

            // Then
            assertTrue(result.contains(testDirectory));
        }
    }

    private File createTestDirectory(String directoryName, int numFiles) throws IOException {
        File testDirectory = Files.createTempDirectory(directoryName).toFile();;
        testDirectory.mkdir();

        for (int i = 0; i < numFiles; i++) {
            File file = new File(testDirectory, "File" + i + ".txt");
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return testDirectory;
    }
}
