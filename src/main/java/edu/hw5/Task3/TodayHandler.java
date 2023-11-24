package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

record TodayHandler(DateParserHandler successor) implements DateParserHandler {
    @Override
    public Optional<LocalDate> handleRequest(String string) {
        if (string.equalsIgnoreCase("today")) {
            return Optional.of(LocalDate.now());
        } else {
            return successor.handleRequest(string);
        }
    }
}
