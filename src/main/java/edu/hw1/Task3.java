package edu.hw1;

import java.util.Arrays;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        if (!isValidArrays(arr1, arr2)) {
            return false;
        }

        int minArr1 = Arrays.stream(arr1).min().getAsInt();
        int maxArr1 = Arrays.stream(arr1).max().getAsInt();
        int minArr2 = Arrays.stream(arr2).min().getAsInt();
        int maxArr2 = Arrays.stream(arr2).max().getAsInt();

        return minArr1 > minArr2 && maxArr1 < maxArr2;
    }

    public static boolean isValidArrays(int[] arr1, int[] arr2) {
        return !((arr1 == null || arr1.length == 0) || (arr2 == null || arr2.length == 0));

    }
}
