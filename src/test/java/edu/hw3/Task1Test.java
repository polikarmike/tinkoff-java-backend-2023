package edu.hw3;

import edu.hw3.task1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Task1Test {
    @Test
    @DisplayName("Шифрование строки в нижнем регистре")
    public void testEncryptWithLowerCase() {
        // Given
        String input = "abc";

        // When
        String result = Task1.atbash(input);

        // Then
        String expected = "zyx";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Шифрование строки в верхнем регистре")
    public void testEncryptWithUpperCase() {
        // Given
        String input = "XYZ";

        // When
        String result = Task1.atbash(input);

        // Then
        String expected = "CBA";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Шифрование строки с смешанным регистром")
    public void testEncryptWithMixedCase() {
        // Given
        String input = "AbC";

        // When
        String result = Task1.atbash(input);

        // Then
        String expected = "ZyX";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Шифрование строки с не-латинскими символами")
    public void testEncryptWithNonAlphabeticCharacters() {
        // Given
        String input = "123!@ЛатинскиеБуквы";

        // When
        String result = Task1.atbash(input);

        // Then
        String expected = "123!@ЛатинскиеБуквы";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Шифрование короткой строки со знаками")
    public void testEncryptShort() {
        // Given
        String input = "Hello world!";

        // When
        String result = Task1.atbash(input);

        // Then
        String expected = "Svool dliow!";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Шифрование длинной строки")
    public void testEncryptLong() {
        // Given
        String input = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";

        // When
        String result = Task1.atbash(input);

        // Then
        String expected = "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        assertEquals(expected, result);
    }
}
