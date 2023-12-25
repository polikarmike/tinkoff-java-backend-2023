package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task1 {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;

    private Task1() {

    }

    public static String calculateAverageTime(String[] sessionData) {
        if (sessionData == null || sessionData.length == 0) {
            throw new IllegalArgumentException("Данные отсутствуют");
        }

        Duration totalDuration = Duration.ZERO;

        for (String sessionStr : sessionData) {
            String[] sessionParts = sessionStr.split(" - ");
            if (sessionParts.length != 2) {
                throw new IllegalArgumentException("Неверный формат данных сеанса");
            }

            try {
                LocalDateTime startTime = LocalDateTime.parse(sessionParts[0], DATE_TIME_FORMATTER);
                LocalDateTime endTime = LocalDateTime.parse(sessionParts[1], DATE_TIME_FORMATTER);

                if (startTime.isAfter(endTime)) {
                    throw new IllegalArgumentException("Неправильный порядок времени в данных сеанса");
                }

                Duration sessionDuration = Duration.between(startTime, endTime);
                totalDuration = totalDuration.plus(sessionDuration);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Неверный формат даты или времени");
            }
        }

        long averageSeconds = totalDuration.getSeconds() / sessionData.length;
        long hours = averageSeconds / SECONDS_PER_HOUR;
        long minutes = (averageSeconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;

        return hours + "ч " + minutes + "м";
    }
}
