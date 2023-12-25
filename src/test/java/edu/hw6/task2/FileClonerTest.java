package edu.hw6.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileClonerTest {

    @Test
    @DisplayName("Создание нескольких уникальных копий")
    void createUniqueCopiesTest() throws IOException {
        // Given
        Path originalFilePath = Paths.get("src/test/java/edu/hw6/task2/file.txt");
        Files.createFile(originalFilePath);

        // When
        FileCloner.cloneFile(originalFilePath);
        FileCloner.cloneFile(originalFilePath);
        FileCloner.cloneFile(originalFilePath);

        // Then
        assertTrue(Files.exists(Paths.get("src/test/java/edu/hw6/task2/file — копия.txt")));
        assertTrue(Files.exists(Paths.get("src/test/java/edu/hw6/task2/file — копия (2).txt")));
        assertTrue(Files.exists(Paths.get("src/test/java/edu/hw6/task2/file — копия (3).txt")));

        // Clean up
        Files.delete(originalFilePath);
        Files.delete(Paths.get("src/test/java/edu/hw6/task2/file — копия.txt"));
        Files.delete(Paths.get("src/test/java/edu/hw6/task2/file — копия (2).txt"));
        Files.delete(Paths.get("src/test/java/edu/hw6/task2/file — копия (3).txt"));
    }

    @Test
    @DisplayName("Клонирование несуществующего файла")
    void testCloneFileWithException() {
        // Given
        Path nonExistentFilePath = Paths.get("nonexistentfile.txt");

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            FileCloner.cloneFile(nonExistentFilePath);
        });
    }
}

