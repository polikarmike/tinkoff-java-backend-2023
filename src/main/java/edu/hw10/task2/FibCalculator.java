package edu.hw10.task2;

interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
