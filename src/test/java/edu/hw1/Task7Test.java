package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Проверка циклического сдвига влево положительного числа")
    void testRotateLeftPositive() {
        // given
        int n = 16;
        int shift = 1;

        // when
        int result = Task7.rotateLeft(n, shift);

        // then
        assertThat(result)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка циклического сдвига вправо положительного числа")
    void testRotateRightPositive() {
        // given
        int n = 8;
        int shift = 1;

        // when
        int result = Task7.rotateRight(n, shift);

        // then
        assertThat(result)
            .isEqualTo(4);
    }
    @Test
    @DisplayName("Проверка циклического сдвига влево отрицательного числа")
    void testRotateLeftNegative() {
        // given
        int n = -16;
        int shift = 1;

        // when
        int result = Task7.rotateLeft(n, shift);

        // then
        assertThat(result)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка циклического сдвига вправо отрицательного числа")
    void testRotateRightNegative() {
        // given
        int n = -8;
        int shift = 1;

        // when
        int result = Task7.rotateRight(n, shift);

        // then
        assertThat(result)
            .isEqualTo(-4);
    }

    @Test
    @DisplayName("Проверка циклического сдвига влево с большим сдвигом")
    void testRotateLeftLargeShift() {
        // given
        int n = 17;
        int shift = 5;

        // when
        int result = Task7.rotateLeft(n, shift);

        // then
        assertThat(result)
            .isEqualTo(17);
    }

    @Test
    @DisplayName("Проверка циклического сдвига вправо с большим сдвигом")
    void testRotateRightLargeShift() {
        // given
        int n = 255;
        int shift = 8;

        // when
        int result = Task7.rotateRight(n, shift);

        // then
        assertThat(result)
            .isEqualTo(255);
    }


}
