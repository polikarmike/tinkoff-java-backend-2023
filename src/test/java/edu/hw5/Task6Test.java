package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {

    @Test
    @DisplayName("Проверка корректной подпоследовательности")
    void testCorrectSubsequence() {
        // Given
        String s = "abc";
        String t = "ahbgdc";

        // When
        boolean result = Task6.isSubsequence(s, t);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка некорректной подпоследовательности")
    void testIncorrectSubsequence() {
        // Given
        String s = "axc";
        String t = "ahbgdc";

        // When
        boolean result = Task6.isSubsequence(s, t);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка пустой строки как подпоследовательности")
    void testEmptyStringAsSubsequence() {
        // Given
        String s = "";
        String t = "ahbgdc";

        // When
        boolean result = Task6.isSubsequence(s, t);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка подпоследовательности для пустой строки")
    void testSubsequenceForEmptyString() {
        // Given
        String s = "abc";
        String t = "";

        // When
        boolean result = Task6.isSubsequence(s, t);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка на IllegalArgumentException при null строке")
    void testNullString() {
        // Given
        String s = "abc";
        String t = null;

        // When/Then
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> Task6.isSubsequence(s, t));
        assertEquals("Обе строки должны быть непустыми", exception.getMessage());
    }
}

