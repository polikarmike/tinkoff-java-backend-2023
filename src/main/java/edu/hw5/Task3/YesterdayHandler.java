package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

record YesterdayHandler(DateParserHandler successor) implements DateParserHandler {
    @Override
    public Optional<LocalDate> handleRequest(String string) {
        if (string.equalsIgnoreCase("yesterday")) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else {
            return successor.handleRequest(string);
        }
    }
}
