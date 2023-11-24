package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {

    @Test
    @DisplayName("Проверка корректного номера автомобиля")
    void testCorrectLicensePlate() {
        // Given
        String licensePlate = "А123ВС45";

        // When
        boolean result = Task5.validateLicensePlate(licensePlate);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка некорректного номера автомобиля")
    void testIncorrectLicensePlate() {
        // Given
        String licensePlate = "12345";

        // When
        boolean result = Task5.validateLicensePlate(licensePlate);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка номера автомобиля с null")
    void testNullLicensePlate() {
        // Given
        String licensePlate = null;

        // When
        boolean result = Task5.validateLicensePlate(licensePlate);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка номера автомобиля без букв")
    void testLicensePlateWithoutLetters() {
        // Given
        String licensePlate = "123456";

        // When
        boolean result = Task5.validateLicensePlate(licensePlate);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Проверка номера автомобиля с неправильной буквой")
    void testLicensePlateWithIncorrectLetter() {
        // Given
        String licensePlate = "A123BC45";

        // When
        boolean result = Task5.validateLicensePlate(licensePlate);

        // Then
        assertFalse(result);
    }
}

