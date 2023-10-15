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

            numStr = createPalindromeDescendant(numStr);
        }
    }

    private static String createPalindromeDescendant(String numStr) {
        StringBuilder newNumStr = new StringBuilder();

        for (int i = 0; i < numStr.length() - 1; i += 2) {
            int sum = sumDigits(numStr.charAt(i), numStr.charAt(i + 1));
            newNumStr.append(sum);
        }

        if (numStr.length() % 2 != 0) {
            newNumStr.append(numStr.charAt(numStr.length() - 1));
        }

        return newNumStr.toString();
    }

    private static int sumDigits(char digit1, char digit2) {
        return Character.getNumericValue(digit1) + Character.getNumericValue(digit2);
    }

    private static boolean isPalindrome(String str) {
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
