package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class EndHandler implements DateParserHandler {
    @Override
    public Optional<LocalDate> handleRequest(String string) {
        return Optional.empty();
    }
}
