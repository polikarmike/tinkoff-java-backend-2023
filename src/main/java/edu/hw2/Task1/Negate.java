package edu.hw2.Task1;

public record Negate(Expr operand) implements Expr {
    @Override
    public double evaluate() {
        return -operand.evaluate();
    }
}
