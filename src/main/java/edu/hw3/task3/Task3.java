package edu.hw3.task3;

import java.util.HashMap;

public class Task3 {
    private Task3() {

    }

    public static <T> HashMap<T, Integer> freqDict(T[] inputList) {
        HashMap<T, Integer> frequencyMap = new HashMap<>();

        for (T item : inputList) {
            if (frequencyMap.containsKey(item)) {
                frequencyMap.put(item, frequencyMap.get(item) + 1);
            } else {
                frequencyMap.put(item, 1);
            }
        }

        return frequencyMap;
    }
}
