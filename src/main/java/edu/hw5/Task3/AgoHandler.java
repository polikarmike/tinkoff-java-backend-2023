package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

record AgoHandler(DateParserHandler successor) implements DateParserHandler {
    @Override
    public Optional<LocalDate> handleRequest(String string) {
        if (string.matches("\\d+ days? ago")) {
            int daysAgo = Integer.parseInt(string.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(daysAgo));
        } else {
            return successor.handleRequest(string);
        }
    }
}
