package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {
    private Task8() {}

    // Первое регулярное выражения
    public static boolean isOddLengthString(String input) {
        String regex = "^(0|1)([01]{2})*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    // Второе регулярное выражения
    public static boolean isStartsWithZeroOddLengthOrStartsWithOneEvenLength(String input) {
        String regex = "(^0([01]{2})*$)|(^1[01]([01]{2})*$)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    // Третье регулярное выражения
    public static boolean hasZeroCountMultipleOfThree(String inputStr) {
        String pattern = "^(1*01*01*0)+1*$";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(inputStr);

        return matcher.matches();
    }

    // Четвертое регулярное выражения
    public static boolean isStringValidExcluding11And111(String input) {
        String regex = "^(?!11$)(?!111$)[01]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    // Пятое регулярное выражения
    public static boolean isContainsOnlyOnesAtOddPositions(String input) {
        String regex = "^(?:1[01])*1?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    // Шестое регулярное выражения
    public static boolean isMoreTwoZeroAndBeloveOneOne(String input) {
        String regex = "^(?=.*0.*0)(?!.*1.*1)[01]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    // Седьмое регулярное выражения
    public static boolean hasNoConsecutiveOnes(String input) {
        String regex = "^(?!.*11.*)[01]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}



