package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {

    @Test
    @DisplayName("Проверка пароля с специальными символами")
    void testWithSpecialCharacters() {
        // Given
        String password = "Password@123";

        // When
        boolean result = Task4.validatePassword(password);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка пароля без специальных символов")
    void testWithoutSpecialCharacters() {
        // Given
        String password = "Password123";

        // When
        boolean result = Task4.validatePassword(password);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка пароля с пустой строкой")
    void testEmptyString() {
        // Given
        String password = "";

        // When
        boolean result = Task4.validatePassword(password);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка пароля с null")
    void testNullInput() {
        // Given
        String password = null;

        // When
        boolean result = Task4.validatePassword(password);

        // Then
        assertFalse(result);
    }
}

