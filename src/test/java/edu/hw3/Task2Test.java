package edu.hw3;

import edu.hw3.task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class Task2Test {

        @Test
        @DisplayName("Пустая строка - пустой список")
        public void emptyStringReturnsEmptyList() {
            // Given
            String input = "";

            // When
            List<String> result = Task2.clusterize(input);

            // Then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Балансированные скобки")
        public void balancedBrackets() {
            // Given
            String input = "((()))()";

            // When
            List<String> result = Task2.clusterize(input);

            // Then
            assertEquals(2, result.size());
            assertEquals("((()))", result.get(0));
            assertEquals("()", result.get(1));
        }

        @Test
        @DisplayName("Небалансированные скобки")
        public void unbalancedBrackets() {
            // Given
            String input = "(()(()))(";

            // When and Then
            assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(input));
        }

}
