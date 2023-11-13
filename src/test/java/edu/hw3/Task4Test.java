package edu.hw3;

import edu.hw3.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Task4Test {

    @Test
    @DisplayName("Преобразование 1 в римское число (I)")
    public void whenValidNumber1ThenRomanI() {
        // Given
        int number = 1;

        // When

        String romanNumber = Task4.convertToRoman(number);

        // Then
        assertEquals("I", romanNumber);
    }

    @Test
    @DisplayName("Преобразование 4 в римское число (IV)")
    public void whenValidNumber4ThenRomanIV() {
        // Given
        int number = 4;

        // When
        String romanNumber = Task4.convertToRoman(number);

        // Then
        assertEquals("IV", romanNumber);
    }

    @Test
    @DisplayName("Преобразование 9 в римское число (IX)")
    public void whenValidNumber9ThenRomanIX() {
        // Given
        int number = 9;

        // When
        String romanNumber = Task4.convertToRoman(number);

        // Then
        assertEquals("IX", romanNumber);
    }

    @Test
    @DisplayName("Преобразование 49 в римское число (XLIX)")
    public void whenValidNumber49ThenRomanXLIX() {
        // Given
        int number = 49;

        // When
        String romanNumber = Task4.convertToRoman(number);

        // Then
        assertEquals("XLIX", romanNumber);
    }

    @Test
    @DisplayName("Преобразование 399 в римское число (CCCXCIX)")
    public void whenValidNumber399ThenRomanCCCXCIX() {
        // Given
        int number = 399;

        // When
        String romanNumber = Task4.convertToRoman(number);

        // Then
        assertEquals("CCCXCIX", romanNumber);
    }

    @Test
    @DisplayName("Преобразование 0 в недопустимое число")
    public void whenNumberIsZeroThenInvalidNumber() {
        // Given
        int number = 0;

        // When
        String romanNumber = Task4.convertToRoman(number);

        // Then
        assertEquals("Недопустимое число", romanNumber);
    }

    @Test
    @DisplayName("Преобразование 4000 в недопустимое число")
    public void whenNumberIsTooLargeThenInvalidNumber() {
        // Given
        int number = 4000;

        // When
        String romanNumber = Task4.convertToRoman(number);

        // Then
        assertEquals("Недопустимое число", romanNumber);
    }

}
