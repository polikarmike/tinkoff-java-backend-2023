package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Проверка вложения arr1 в arr2")
    void testNestableArrays() {
        // given
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {0, 1, 2, 3, 4};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка отсутствия вложения (оба массива равны)")
    void testEqualArrays() {
        // given
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Проверка отсутствия вложения (нет общих элементов)")
    void testNoCommonElements() {
        // given
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Проверка пустого массива")
    void testEmptyArrays() {
        // given
        int[] arr1 = {};
        int[] arr2 = {1, 2, 3};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Проверка null-массива")
    void testNullArray() {
        // given
        int[] arr1 = null;
        int[] arr2 = {1, 2, 3};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Проверка вложения arr1 в arr2")
    void testNestableArrays4() {
        // given
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {0, 1, 2, 3, 4};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка вложения arr1 в arr2 (случай с разными элементами)")
    void testNestableArrays2() {
        // given
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {0, 6};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка вложения arr1 в arr2 (обратный порядок)")
    void testNestableArrays3() {
        // given
        int[] arr1 = {3, 1};
        int[] arr2 = {4, 0};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Проверка отсутствия вложения (разные элементы)")
    void testNoNestingDifferentElements() {
        // given
        int[] arr1 = {9, 9, 8};
        int[] arr2 = {8, 9};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isFalse();
    }
}
