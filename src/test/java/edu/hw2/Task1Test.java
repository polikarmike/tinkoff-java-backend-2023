package edu.hw2;

import edu.hw2.Task1.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Проверка сложных вычислений")
    void testComplexExpression() {
        // given
        Expr two = new Constant(2);
        Expr four = new Constant(4);
        Expr negOne = new Negate(new Constant(1));
        Expr sumTwoFour = new Addition(two, four);
        Expr mult = new Multiplication(sumTwoFour, negOne);
        Expr exp = new Exponent(mult, 2);
        Expr expected = new Addition(exp, new Constant(1));

        // when
        double result = expected.evaluate();

        // then
        assertThat(result).isEqualTo(37.0);
    }

    @Test
    @DisplayName("Проверка константы")
    void testConstant() {
        // given
        Expr constantExpr = new Constant(5.0);

        // when
        double result = constantExpr.evaluate();

        // then
        assertThat(result).isEqualTo(5.0);
    }

    @Test
    @DisplayName("Проверка отрицания")
    void testNegate() {
        // given
        Expr constantExpr = new Constant(5.0);
        Expr negateExpr = new Negate(constantExpr);

        // when
        double result = negateExpr.evaluate();

        // then
        assertThat(result).isEqualTo(-5.0);
    }

    @Test
    @DisplayName("Проверка возведения в степень")
    void testExponent() {
        // given
        Expr baseExpr = new Constant(2.0);
        Expr exponentExpr = new Exponent(baseExpr, 3);

        // when
        double result = exponentExpr.evaluate();

        // then
        assertThat(result).isEqualTo(8.0);
    }

    @Test
    @DisplayName("Проверка сложения")
    void testAddition() {
        // given
        Expr leftExpr = new Constant(2.0);
        Expr rightExpr = new Constant(3.0);
        Expr additionExpr = new Addition(leftExpr, rightExpr);

        // when
        double result = additionExpr.evaluate();

        // then
        assertThat(result).isEqualTo(5.0);
    }

    @Test
    @DisplayName("Проверка умножения")
    void testMultiplication() {
        // given
        Expr leftExpr = new Constant(2.0);
        Expr rightExpr = new Constant(3.0);
        Expr multiplicationExpr = new Multiplication(leftExpr, rightExpr);

        // when
        double result = multiplicationExpr.evaluate();

        // then
        assertThat(result).isEqualTo(6.0);
    }
}
