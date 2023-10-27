package edu.hw3;

import edu.hw3.task7.NullComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Добавление null в TreeMap и проверка наличия")
    public void testAddNullToTreeMap() {
        // Given
        TreeMap<String, String> treeMap = new TreeMap<>(new NullComparator<>());

        // When
        treeMap.put(null, "test");

        // Then
        assertThat(treeMap.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Сравнение с использованием NullComparator в TreeMap (вариант 1)")
    public void testCompareWithNullComparator1() {
        // Given
        TreeMap<String, String> treeMap = new TreeMap<>(new NullComparator<>());

        // When
        treeMap.put("key1", "value1");
        treeMap.put(null, "value2");

        // Then
        assertThat(treeMap.firstKey()).isEqualTo(null);
    }

    @Test
    @DisplayName("Сравнение с использованием NullComparator в TreeMap (вариант 2)")
    public void testCompareWithNullComparator2() {
        // Given
        TreeMap<String, String> treeMap = new TreeMap<>(new NullComparator<>());

        // When
        treeMap.put(null, "value1");
        treeMap.put("key1", "value2");

        // Then
        assertThat(treeMap.firstKey()).isEqualTo(null);
    }
}
