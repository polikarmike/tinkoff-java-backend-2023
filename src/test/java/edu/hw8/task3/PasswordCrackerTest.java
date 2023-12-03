package edu.hw8.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordCrackerTest {
    @Test
    @DisplayName("Проверка однопоточного раскрытия пароля")
    public void testDecodePasswordsSignleThread() throws NoSuchAlgorithmException {
        // Given
        Map<String, String> passwordHashes = new HashMap<>();
        passwordHashes.put("user1", "c20ad4d76fe97759aa27a0c99bff6710");
        passwordHashes.put("user2", "e529a9cea4a728eb9c5828b13b22844c");

        // When
        Map<String, String> crackedPasswords = SingleThreadedPasswordCracker.decode(passwordHashes);

        // Then
        assertEquals(2, crackedPasswords.size());

        assertEquals("12", crackedPasswords.get("user1"));
        assertEquals("pa", crackedPasswords.get("user2"));

    }

    @Test
    @DisplayName("Проверка мультипоточного раскрытия пароля")
    public void testDecodePasswordsMultiThread() throws NoSuchAlgorithmException, InterruptedException {
        // Given
        Map<String, String> passwordHashes = new HashMap<>();
        passwordHashes.put("user1", "c20ad4d76fe97759aa27a0c99bff6710");
        passwordHashes.put("user2", "e529a9cea4a728eb9c5828b13b22844c");
        int numThreads = 4;

        // When
        Map<String, String> crackedPasswords = MultiThreadedPasswordCracker.decode(passwordHashes, numThreads);

        // Then
        assertEquals(2, crackedPasswords.size());

        assertEquals("12", crackedPasswords.get("user1"));
        assertEquals("pa", crackedPasswords.get("user2"));

    }

    @Test
    @DisplayName("Проверка создания отчета")
    void testCreateReport() throws IOException {
        // Given
        String filePath = "src/test/java/edu/hw8/task3/local_password_results.txt";
        Map<String, String> passwordHashes = new HashMap<>();
        passwordHashes.put("user1", "c20ad4d76fe97759aa27a0c99bff6710");
        passwordHashes.put("user2", "e529a9cea4a728eb9c5828b13b22844c");
        int numThreads = 4;

        // When
        Reporter.createReport(filePath, passwordHashes, numThreads);

        // Then
        assertTrue(new java.io.File(filePath).exists());
    }


}
