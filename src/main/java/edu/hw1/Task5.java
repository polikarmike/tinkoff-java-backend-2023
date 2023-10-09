package edu.hw1;

public class Task5 {
    private Task5() {

    }

    public static boolean isPalindromeDescendant(int number) {
        String numStr = Integer.toString(number);

        while (true) {
            if (numStr.length() < 2) {
                return false;
            }

            if (isPalindrome(numStr)) {
                return true;
            }

            StringBuilder newNumStr = new StringBuilder();
            for (int i = 0; i < numStr.length() - 1; i += 2) {
                int digit1 = Character.getNumericValue(numStr.charAt(i));
                int digit2 = Character.getNumericValue(numStr.charAt(i + 1));
                int sum = digit1 + digit2;
                newNumStr.append(sum);
            }

            numStr = newNumStr.toString();
        }
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
