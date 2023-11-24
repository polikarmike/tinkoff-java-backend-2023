package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {
    }

    private static final Pattern LICENSE_PLATE_PATTERN =
        Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    public static boolean validateLicensePlate(String licensePlate) {
        if (licensePlate == null) {
            return false;
        }
        return LICENSE_PLATE_PATTERN.matcher(licensePlate).matches();
    }
}
