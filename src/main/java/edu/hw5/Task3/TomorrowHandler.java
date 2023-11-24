package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

record TomorrowHandler(DateParserHandler successor) implements DateParserHandler {

    @Override
    public Optional<LocalDate> handleRequest(String string) {
        if (string.equalsIgnoreCase("tomorrow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else {
            return successor.handleRequest(string);
        }
    }
}
