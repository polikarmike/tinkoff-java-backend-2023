package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task7Test {

    @Test
    @DisplayName("Проверка наличия хотя бы 3 символов с третьим 0")
    void testAtLeast3CharsWithThird0() {
        // Given
        String input = "010101";

        // When
        boolean result = Task7.hasAtLeast3CharsWithThird0(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка отсутствия хотя бы 3 символов")
    void testLessThan3CharsWithoutThird0() {
        // Given
        String input = "11";

        // When
        boolean result = Task7.hasAtLeast3CharsWithThird0(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка отсутствия третьего 0")
    void testAtLeast3CharsWithoutThird0() {
        // Given
        String input = "111";

        // When
        boolean result = Task7.hasAtLeast3CharsWithThird0(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка, что строка начинается и заканчивается одним и тем же символом")
    void testStartsAndEndsWithSameChar() {
        // Given
        String input = "0110";

        // When
        boolean result = Task7.startsAndEndsWithSameChar(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка, что строка не начинается и не заканчивается одним и тем же символом")
    void testNotStartsAndEndsWithSameChar() {
        // Given
        String input = "0101";

        // When
        boolean result = Task7.startsAndEndsWithSameChar(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка, что длина не менее 1 и не более 3")
    void testLengthBetween1And3() {
        // Given
        String input = "101";

        // When
        boolean result = Task7.hasLengthBetween1And3(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка, что длина более 3")
    void testLengthMore3() {
        // Given
        String input = "01010";

        // When
        boolean result = Task7.hasLengthBetween1And3(input);

        // Then
        assertFalse(result);
    }
}

