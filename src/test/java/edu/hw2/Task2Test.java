package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle(4,5)),
            Arguments.of(new Square(3))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Проверка принципа подстановки Лисков")
    void liskovSubstitutionPrinciple(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Проверка вычисления площади прямоугольника")
    void testRectangleArea() {
        // given
        Rectangle rectangle = new Rectangle(4, 5);

        // when
        double area = rectangle.area();

        // then
        assertThat(area).isEqualTo(20.0);
    }

    @Test
    @DisplayName("Проверка вычисления площади квадрата")
    void testSquareArea() {
        // given
        Square square = new Square(3);

        // when
        double area = square.area();

        // then
        assertThat(area).isEqualTo(9.0);
    }
}
