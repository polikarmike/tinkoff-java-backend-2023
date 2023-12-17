package edu.hw11;

import edu.hw11.task3.FibonacciCodeAppender;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    @DisplayName("Проверка Фибоначчи для нуля")
    public void testFibonacciOfZero() throws Exception {
        // Given
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", int.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciCodeAppender()))
            .make();

        Class<?> clazz = dynamicType.load(Task3Test.class.getClassLoader()).getLoaded();

        // When
        int result = (int) clazz.getMethod("fib", int.class).invoke(null, 0);

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Проверка Фибоначчи для отрицательного числа (возращает тоже самое число)")
    public void testFibonacciOfNegativeNumber() throws Exception {
        // Given
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", int.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciCodeAppender()))
            .make();

        Class<?> clazz = dynamicType.load(Task3Test.class.getClassLoader()).getLoaded();

        // When
        int result = (int) clazz.getMethod("fib", int.class).invoke(null, -5);

        // Then
        assertEquals(-5, result);
    }

    @Test
    @DisplayName("Проверка Фибоначчи для корректного числа")
    public void testFibonacciOfCorrectNumber() throws Exception {
        // Given
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", int.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciCodeAppender()))
            .make();

        Class<?> clazz = dynamicType.load(Task3Test.class.getClassLoader()).getLoaded();

        // When
        int result = (int) clazz.getMethod("fib", int.class).invoke(null, 7);

        // Then
        assertEquals(13, result);
    }

    @Test
    @DisplayName("Проверка Фибоначчи для большого числа")
    public void testFibonacciOfLargeNumber() throws Exception {
        // Given
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", int.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciCodeAppender()))
            .make();

        Class<?> clazz = dynamicType.load(Task3Test.class.getClassLoader()).getLoaded();

        // When
        int result = (int) clazz.getMethod("fib", int.class).invoke(null, 20);

        // Then
        assertEquals(6765, result);
    }
}
