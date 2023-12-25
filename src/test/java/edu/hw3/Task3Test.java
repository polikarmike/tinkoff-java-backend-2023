package edu.hw3;


import edu.hw3.task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;


public class Task3Test {

    @Test
    @DisplayName("Одиночный элемент в массиве")
    public void testFreqDictWithOneElement() {
        // Given
        Integer[] inputList = {1};

        // When
        HashMap<Integer, Integer> result = Task3.freqDict(inputList);

        // Then
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Несколько элементов в массиве")
    public void testFreqDictWithMultipleElements() {
        // Given
        String[] inputList = {"apple", "banana", "apple", "cherry", "banana"};

        // When
        HashMap<String, Integer> result = Task3.freqDict(inputList);

        // Then
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("apple", 2);
        expected.put("banana", 2);
        expected.put("cherry", 1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Пустой массив")
    public void testFreqDictWithEmptyArray() {
        // Given
        Double[] inputList = new Double[0];

        // When
        HashMap<Double, Integer> result = Task3.freqDict(inputList);

        // Then
        assertTrue(result.isEmpty());
    }
}
