package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Проверка, что ни один конь не может захватить другого коня.")
    void testKnightBoardCaptureExample1() {
        // given
        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // when
        boolean result = Task8.knightBoardCapture(board);

        // then
        assertThat(result).isTrue();
        }

    @Test
    @DisplayName("Проверка, что конь может захватить другого коня. (1)")
    void testKnightBoardCaptureExample2() {
        // given
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        // when
        boolean result = Task8.knightBoardCapture(board);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Проверка, что конь может захватить другого коня. (2)")
    void testKnightBoardCaptureExample3() {
        // given
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean result = Task8.knightBoardCapture(board);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Проверка неверного размера шахматной доски")
    void testInvalidKnightBoardSize() {
        // given
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0},
        };

        // when
        Throwable thrown = catchThrowable(() -> {
            Task8.knightBoardCapture(board);
        });

        // then
        assertThat(thrown)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Размер шахматной доски должен быть 8x8");
    }

    @Test
    @DisplayName("Проверка пустой шахматной доски")
    void testEmptyKnightBoard() {
        // given
        int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean result = Task8.knightBoardCapture(board);

        // then
        assertThat(result).isTrue();
    }
}
