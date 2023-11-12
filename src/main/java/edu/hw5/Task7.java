package edu.hw5;

public class Task7 {
    private Task7() {}

    public static boolean hasAtLeast3CharsWithThird0(String input) {

        return input.matches("^[01]{2}0[01]*$");
    }

    public static boolean startsAndEndsWithSameChar(String input) {

        return input.matches("^([01])[0|1]*\\1$");
    }

    public static boolean hasLengthBetween1And3(String input) {

        return input.matches("^[01]{1,3}$");
    }

}
