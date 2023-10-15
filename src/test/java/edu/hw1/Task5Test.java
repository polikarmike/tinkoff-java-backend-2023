package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Проверка с палиндромом (11)")
    void testPalindrome() {
        // given
        int n = 11;

        // when
        boolean result = Task5.isPalindromeDescendant(n);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка с числом без палиндромного потомка (1234)")
    void testNoPalindromeDescendant() {
        // given
        int n = 1234;

        // when
        boolean result = Task5.isPalindromeDescendant(n);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Проверка с числом и палиндромным потомком (11211230)")
    void testPalindromeDescendant() {
        // given
        int n = 11211230;

        // when
        boolean result = Task5.isPalindromeDescendant(n);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка с числом и палиндромным потомком (1234567890)")
    void testPalindromeDescendant1() {
        // given
        int n = 1234567890;

        // when
        boolean result = Task5.isPalindromeDescendant(n);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка с числом и палиндромным потомком (13001120)")
    void testPalindromeDescendant2() {
        // given
        int n = 13001120;

        // when
        boolean result = Task5.isPalindromeDescendant(n);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка с числом и палиндромным потомком (23336014)")
    void testPalindromeDescendant3() {
        // given
        int n = 23336014;

        // when
        boolean result = Task5.isPalindromeDescendant(n);

        // then
        assertThat(result).isTrue();
    }
}
