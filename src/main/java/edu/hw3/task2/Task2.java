package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task2 {
    private Task2() {}

    public static List<String> clusterize(String input) {
        List<String> clusters = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int start = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
                if (start == -1) {
                    start = i;
                }
            } else if (input.charAt(i) == ')' && !stack.isEmpty()) {
                stack.pop();
                if (stack.isEmpty()) {
                    clusters.add(input.substring(start, i + 1));
                    start = -1;
                }
            }
        }

        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Несбалансированные скобки: " + stack.size());
        }

        return clusters;
    }

}


