package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

    @Test
    @DisplayName("Расчет среднего времени для корректных данных")
    void testValidData() {
        // Given
        String[] sessionData = {"2023-01-01, 10:00 - 2023-01-01, 12:00", "2023-01-01, 13:00 - 2023-01-01, 15:00"};

        // When
        String result = Task1.calculateAverageTime(sessionData);

        // Then
        assertEquals("2ч 0м", result);
    }

    @Test
    @DisplayName("Обработка отсутствия данных")
    void testNullData() {
        // Given
        String[] sessionData = null;

        // When/Then
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> Task1.calculateAverageTime(sessionData));
        assertEquals("Данные отсутствуют", exception.getMessage());
    }

    @Test
    @DisplayName("Обработка неверного формата данных сеанса")
    void testInvalidSessionFormat() {
        // Given
        String[] sessionData = {"2023-01-01, 10:00 - 2023-01-01, 12:00", "InvalidSessionFormat"};

        // When/Then
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> Task1.calculateAverageTime(sessionData));
        assertEquals("Неверный формат данных сеанса", exception.getMessage());
    }

    @Test
    @DisplayName("Обработка неправильного порядка времени в данных сеанса")
    void testInvalidSessionOrder() {
        // Given
        String[] sessionData = {"2023-01-01, 12:00 - 2023-01-01, 10:00"};

        // When/Then
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> Task1.calculateAverageTime(sessionData));
        assertEquals("Неправильный порядок времени в данных сеанса", exception.getMessage());
    }

    @Test
    @DisplayName("Обработка неверного формата даты или времени")
    void testInvalidDateTimeFormat() {
        // Given
        String[] sessionData = {"2023/01/01, 10:00 - 2023/01/01, 12:00"};

        // When/Then
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> Task1.calculateAverageTime(sessionData));
        assertEquals("Неверный формат даты или времени", exception.getMessage());
    }
}
