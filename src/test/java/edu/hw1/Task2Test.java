package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка числа 0")
    void testZero() {
        // given
        int number = 0;

        // when
        int result = Task2.countDigits(number);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка положительного числа")
    void testPositiveNumber() {
        // given
        int number = 12345;

        // when
        int result = Task2.countDigits(number);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка отрицательного числа")
    void testNegativeNumber() {
        // given
        int number = -9876;

        // when
        int result = Task2.countDigits(number);

        // then
        assertThat(result).isEqualTo(4);
    }
}
