package edu.hw3.task3;

import java.util.HashMap;

public class Task3 {
    private Task3() {

    }

    public static <T> HashMap<T, Integer> freqDict(T[] inputList) {
        HashMap<T, Integer> frequencyMap = new HashMap<>();

        for (T item : inputList) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        return frequencyMap;
    }
}
