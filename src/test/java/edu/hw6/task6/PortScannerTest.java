package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class PortScannerTest {

    @Test
    @DisplayName("Cоздание выходного файла")
    public void testOutputFileCreated() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testOutput", ".txt");

        // When
        PortScanner.scanPorts(tempFile.toAbsolutePath().toString());

        // Then
        assertDoesNotThrow(() -> new BufferedReader(new FileReader(tempFile.toFile())));
    }

    @Test
    @DisplayName("Проверка несуществующего порта")
    public void testCheckNonExistentPort() throws IOException {
        // Given
        int nonExistentPort = -1;
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testOutput", ".txt");

        // When
        PortScanner.scanPorts(tempFile.toAbsolutePath().toString());

        // Then
        assertPortIsFree(tempFile.toAbsolutePath().toString(), nonExistentPort);
    }

    private void assertPortIsFree(String outputPath, int port) {
        try (BufferedReader reader = new BufferedReader(new FileReader(outputPath))) {
            String line;
            boolean portFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(String.valueOf(port))) {
                    portFound = true;
                    break;
                }
            }
            assertFalse(portFound);
        } catch (IOException e) {
            fail(e);
        }
    }
}



