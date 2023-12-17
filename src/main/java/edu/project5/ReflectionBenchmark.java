package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;


@State(Scope.Thread)
public class ReflectionBenchmark {
    private Student student;
    private Method directAccessMethod;
    private MethodHandle methodHandle;
    private StudentNameGetter lambdaMetafactoryGetter;

    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(7))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(7))
            .build();

        new Runner(options).run();
    }

    @Setup
    @SuppressWarnings({"MultipleStringLiterals"})
    public void setup() throws NoSuchMethodException, IllegalAccessException, Throwable {
        student = new Student("Alexander", "Biryukov");
        directAccessMethod = Student.class.getMethod("name");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "getName",
            MethodType.methodType(StudentNameGetter.class),
            MethodType.methodType(String.class, Student.class),
            lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class)),
            MethodType.methodType(String.class, Student.class)
        );
        lambdaMetafactoryGetter = (StudentNameGetter) callSite.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) directAccessMethod.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandle(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invokeExact(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = lambdaMetafactoryGetter.getName(student);
        bh.consume(name);
    }

    @FunctionalInterface
    interface StudentNameGetter {
        String getName(Student student);
    }

    record Student(String name, String surname) {}
}


