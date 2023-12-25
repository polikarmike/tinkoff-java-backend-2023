package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {

    @Test
    @DisplayName("(Паттерна №1) Проверка строки на нечетную длину")
    void testOddLengthString() {
        // Given
        String input = "01010";

        // When
        boolean result = Task8.isOddLengthString(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №1) Проверка строки c четной длиной")
    void testNotOddLengthString() {
        // Given
        String input = "0101";

        // When
        boolean result = Task8.isOddLengthString(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №2) Проверка строки на нечетную длину, начинающуюся с 0")
    void testStartsWithZeroOddLength() {
        // Given
        String input = "01010";

        // When
        boolean result = Task8.isStartsWithZeroOddLengthOrStartsWithOneEvenLength(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №2) Проверка строки на четную длину, начинающуюся с 1")
    void testStartsWithOneEvenLength() {
        // Given
        String input = "1010";

        // When
        boolean result = Task8.isStartsWithZeroOddLengthOrStartsWithOneEvenLength(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №2) Проверка строки при четной длине, начинающуюся с 0")
    void testStartsWithZeroEvenLength() {
        // Given
        String input = "0101";

        // When
        boolean result = Task8.isStartsWithZeroOddLengthOrStartsWithOneEvenLength(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №2) Проверка строки при нечетной длине, начинающуюся с 0")
    void testStartsWithOneOddLength() {
        // Given
        String input = "101";

        // When
        boolean result = Task8.isStartsWithZeroOddLengthOrStartsWithOneEvenLength(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №3) Проверка строки при наличие нулей, кратных тройке")
    void testMultipleOfThreeZeros() {
        // Given
        String input = "010101";

        // When
        boolean result = Task8.hasZeroCountMultipleOfThree(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №3) Проверка строки при наличие нулей, не кратных тройке")
    void testNotMultipleOfThreeZeros() {
        // Given
        String input = "0101010101";

        // When
        boolean result = Task8.hasZeroCountMultipleOfThree(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №4) Проверка строки, исключая строки '11' и '111'")
    void testStringValidExcluding11And111() {
        // Given
        String input = "010101";

        // When
        boolean result = Task8.isStringValidExcluding11And111(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №4) Проверка строки при '11'")
    void testcheckString11() {
        // Given
        String input = "11";

        // When
        boolean result = Task8.isStringValidExcluding11And111(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №4) Проверка строки при '111'")
    void testCheckString111() {
        // Given
        String input = "111";

        // When
        boolean result = Task8.isStringValidExcluding11And111(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №5) Проверка строки на содержание единиц на нечетных позициях")
    void testContainsOnesAtOddPositions() {
        // Given
        String input = "101110";

        // When
        boolean result = Task8.isContainsOnlyOnesAtOddPositions(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №5) Проверка строки на содержание нулей на нечетных позициях")
    void testContainsZerosAtOddPositions() {
        // Given
        String input = "000110";

        // When
        boolean result = Task8.isContainsOnlyOnesAtOddPositions(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №6) Проверка строки на наличие более двух нулей и отсутствие больше одной единиц")
    void testMoreTwoZeroAndBeloveOneOne() {
        // Given
        String input = "0100";

        // When
        boolean result = Task8.isMoreTwoZeroAndBeloveOneOne(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №6) Проверка строки при наличие меньше двух нулей")
    void testLessTwoZero() {
        // Given
        String input = "01";

        // When
        boolean result = Task8.isMoreTwoZeroAndBeloveOneOne(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №6) Проверка строки при наличие, больше одной единицы")
    void testMoreOneOne() {
        // Given
        String input = "01001";

        // When
        boolean result = Task8.isMoreTwoZeroAndBeloveOneOne(input);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("(Паттерна №7) Проверка строки на отсутствие последовательных единиц")
    void testNoConsecutiveOnes() {
        // Given
        String input = "010101";

        // When
        boolean result = Task8.hasNoConsecutiveOnes(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("(Паттерна №7) Проверка строки при последовательных единицах")
    void testConsecutiveOnes() {
        // Given
        String input = "0101111101";

        // When
        boolean result = Task8.hasNoConsecutiveOnes(input);

        // Then
        assertFalse(result);
    }
}

