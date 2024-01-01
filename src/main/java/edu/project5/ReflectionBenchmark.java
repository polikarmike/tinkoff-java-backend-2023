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
    private static final String METHOD_NAME = "name";
    private Student student;
    private Method directAccessMethod;
    private MethodHandle methodHandle;
    private StudentNameGetter lambdaMetafactoryGetter;

    @SuppressWarnings({"UncommentedMain"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(BenchmarkConstants.FORKS)
            .warmupForks(BenchmarkConstants.WARMUP_FORKS)
            .warmupIterations(BenchmarkConstants.WARMUP_ITERATIONS)
            .warmupTime(TimeValue.seconds(BenchmarkConstants.WARMUP_TIME_SECONDS))
            .measurementIterations(BenchmarkConstants.MEASUREMENT_ITERATIONS)
            .measurementTime(TimeValue.seconds(BenchmarkConstants.MEASUREMENT_TIME_SECONDS))
            .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() throws NoSuchMethodException, IllegalAccessException, Throwable {
        student = new Student("Alexander", "Biryukov");
        directAccessMethod = Student.class.getMethod(METHOD_NAME);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "getName",
            MethodType.methodType(StudentNameGetter.class),
            MethodType.methodType(String.class, Student.class),
            lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class)),
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
}


