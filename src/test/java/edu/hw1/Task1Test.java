package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка времени с нулевыми секундами")
    void testZeroSeconds() {
        // given
        String time = "01:00";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("Проверка валидного времени")
    void validTime() {
        // given
        String time = "13:56";

        // when
        long evenNumbers = Task1.minutesToSeconds(time);

        // then
        assertThat(evenNumbers)
            .isEqualTo(836);
    }

    @Test
    @DisplayName("Проверка секунд, превышающих 60")
    void testInvalidSeconds() {
        // given
        String time = "10:61";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка отсутствия символа ':'")
    void testMissingColon() {
        // given
        String time = "531";

        // when
        long evenNumbers = Task1.minutesToSeconds(time);

        // then
        assertThat(evenNumbers)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка неверного формата секунд")
    void testInvalidSecondsFormat() {
        // given
        String time = "5:631";

        // when
        long evenNumbers = Task1.minutesToSeconds(time);

        // then
        assertThat(evenNumbers)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка большого количества минут")
    void testLargeMinutes() {
        // given
        String time = "999:59";

        // when
        long evenNumbers = Task1.minutesToSeconds(time);

        // then
        assertThat(evenNumbers)
            .isEqualTo(59999);
    }

    @Test
    @DisplayName("Проверка времени с нулевыми минутами")
    void testZeroMinutes() {
        // given
        String time = "0:45";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(45);
    }

    @Test
    @DisplayName("Проверка времени с неверными символами")
    void testInvalidCharacters() {
        // given
        String time = "ab:cd";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка значения null")
    void testNullValue() {
        // given
        String time = null;

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка значения без чисел, но с разделителем")
    void testNoNumbersWithSeparator() {
        // given
        String time = ":";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка времени с часами")
    void testTimeWithHours() {
        // given
        String time = "14:12:30";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1);
    }
}
