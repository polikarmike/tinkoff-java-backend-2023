package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @Test
    @DisplayName("Проверка hasNext для пустой коллекции")
    public void testHasNextForEmptyCollection() {
        // Given
        List<Integer> emptyList = new ArrayList<>();
        BackwardIterator<Integer> iterator = new BackwardIterator<>(emptyList);

        // When
        boolean hasNext = iterator.hasNext();

        // Then
        assertFalse(hasNext);
    }

    @Test
    @DisplayName("Проверка hasNext для непустой коллекции")
    public void testHasNextForNonEmptyCollection() {
        // Given
        List<String> stringList = Arrays.asList("A", "B", "C");
        BackwardIterator<String> iterator = new BackwardIterator<>(stringList);

        // When
        boolean hasNext = iterator.hasNext();

        // Then
        assertTrue(hasNext);
    }

    @Test
    @DisplayName("Получение элементов в обратном порядке")
    public void testGetElements() {
        // Given
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        BackwardIterator<Double> iterator = new BackwardIterator<>(doubleList);

        // When
        List<Double> reversedList = new ArrayList<>();
        while (iterator.hasNext()) {
            reversedList.add(iterator.next());
        }

        // Then
        List<Double> expectedList = Arrays.asList(3.3, 2.2, 1.1);
        assertIterableEquals(expectedList, reversedList);
    }

    @Test
    @DisplayName("Бросок исключения при обращении к next() после последнего элемента")
    public void testExceptionThrown() {
        // Given
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        BackwardIterator<Integer> iterator = new BackwardIterator<>(integerList);

        // When
        while (iterator.hasNext()) {
            iterator.next();
        }

        // Then
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
