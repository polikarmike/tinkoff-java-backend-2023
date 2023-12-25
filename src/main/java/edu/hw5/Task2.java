package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {}

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> findFridaysThe13th(int year) {
        List<LocalDate> fridaysThe13th = new ArrayList<>();

        for (Month month : Month.values()) {
            LocalDate date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysThe13th.add(date);
            }
        }

        return fridaysThe13th;
    }

    @SuppressWarnings("MagicNumber")
    public static LocalDate findNextFridayThe13th(LocalDate currentDate) {
        LocalDate nextFridayThe13th = currentDate;

        while (true) {
            nextFridayThe13th = nextFridayThe13th.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
            if (nextFridayThe13th.getDayOfMonth() == 13) {
                return nextFridayThe13th;
            }
        }
    }
}
