package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

record DayMonthYearHandler(DateParserHandler successor) implements DateParserHandler {

    @Override
    public Optional<LocalDate> handleRequest(String string) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
            LocalDate date = LocalDate.parse(string, formatter);
            return Optional.of(date);
        } catch (Exception e) {
            return successor.handleRequest(string);
        }
    }
}
