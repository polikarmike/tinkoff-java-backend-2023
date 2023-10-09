package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class Task6Test {
    @Test
    @DisplayName("Проверка с валидным входным значением (1)")
    void testCountKValidInputExample1() {
        // given
        int n = 3524;

        // when
        int result = Task6.countK(n);

        // then
        assertThat(result)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("Проверка с валидным входным значением (2)")
    void testCountKValidInputExample2() {
        // given
        int n = 6621;

        // when
        int result = Task6.countK(n);

        // then
        assertThat(result)
            .isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка с валидным входным значением (3)")
    void testCountKValidInputExample3() {
        // given
        int n = 6554;

        // when
        int result = Task6.countK(n);

        // then
        assertThat(result)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("Проверка с валидным входным значением (4)")
    void testCountKValidInputExample4() {
        // given
        int n = 1234;

        // when
        int result = Task6.countK(n);

        // then
        assertThat(result)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("Проверка с некорректным входным значением (меньше 1000)")
    void testCountKInvalidInputExample1() {
        // given
        int n = 1000; // Неправильное значение (меньше 1000)

        // when
        Throwable thrown = catchThrowable(() -> {
            Task6.countK(n);
        });

        // then
        assertThat(thrown)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Число должно находится в интервале между 1001 и 9998, и не все цифры должны быть одинаковыми.");
    }

    @Test
    @DisplayName("Проверка с некорректным входным значением (больше 9999)")
    void testCountKInvalidInputExample2() {
        // given
        int n = 10000; // Неправильное значение (меньше 1000)

        // when
        Throwable thrown = catchThrowable(() -> {
            Task6.countK(n);
        });

        // then
        assertThat(thrown)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Число должно находится в интервале между 1001 и 9998, и не все цифры должны быть одинаковыми.");
    }

    @Test
    @DisplayName("Проверка число с одинаковыми цифрами")
    void testCountKSameDigits() {
        // given
        int n = 1111; // Все цифры одинаковы

        // when
        Throwable thrown = catchThrowable(() -> {
            Task6.countK(n);
        });

        // then

        assertThat(thrown)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Число должно находится в интервале между 1001 и 9998, и не все цифры должны быть одинаковыми.");
    }
}
