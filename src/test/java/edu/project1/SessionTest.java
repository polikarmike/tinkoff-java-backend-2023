package edu.project1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {
    @Test
    @DisplayName("Проверка правильного угадывания буквы")
    public void testGuessCorrectLetter() {
        // Given
        Session session = new Session("apple", 6);

        // When
        GuessResult result = session.guess('a');

        // Then
        assertEquals("a____", String.valueOf(result.state()));
        assertEquals(0, result.attempt());
        assertEquals(6, result.maxAttempts());
        assertEquals("Keep guessing!", result.message());
    }

    @Test
    @DisplayName("Проверка неправильного угадывания буквы")
    public void testGuessIncorrectLetter() {
        // Given
        Session session = new Session("banana", 6);

        // When
        GuessResult result = session.guess('z');

        // Then
        assertEquals("______", String.valueOf(result.state()));
        assertEquals(1, result.attempt());
        assertEquals(6, result.maxAttempts());
        assertEquals("Keep guessing!", result.message());
    }

    @Test
    @DisplayName("Проверка выигрыша в игре")
    public void testWinGame() {
        // Given
        Session session = new Session("apple", 6);
        session.guess('a');
        session.guess('p');
        session.guess('l');

        // When
        GuessResult result = session.guess('e');

        // Then
        assertEquals("apple", String.valueOf(result.state()));
        assertEquals(0, result.attempt());
        assertEquals(6, result.maxAttempts());
        assertEquals("Congratulations, you've won!", result.message());
    }

    @Test
    @DisplayName("Проверка проигрыша в игре")
    public void testLoseGame() {
        // Given
        Session session = new Session("cherry", 3);
        session.guess('r');
        session.guess('b');
        session.guess('d');

        // When
        GuessResult result = session.guess('z');

        // Then
        assertEquals("___rr_", String.valueOf(result.state()));
        assertEquals(3, result.attempt());
        assertEquals(3, result.maxAttempts());
        assertEquals("You're out of attempts! The word was: cherry", result.message());
    }

    @Test
    @DisplayName("Проверка сдачи")
    public void testGiveUp() {
        // Given
        Session session = new Session("watermelon", 6);

        // When
        GuessResult result = session.giveUp();

        // Then
        assertEquals("__________", String.valueOf(result.state()));
        assertEquals(6, result.attempt());
        assertEquals(6, result.maxAttempts());
        assertEquals("You gave up! The word was: watermelon", result.message());
    }

    @Test
    @DisplayName("Проверка повторного ввода угаданной буквы")
    public void testSameLetter() {
        // Given
        Session session = new Session("apple", 6);
        session.guess('a');

        // When
        GuessResult result = session.guess('a');

        // Then
        assertEquals("a____", String.valueOf(result.state()));
        assertEquals(0, result.attempt());
        assertEquals(6, result.maxAttempts());
        assertEquals("Keep guessing!", result.message());
    }
}

