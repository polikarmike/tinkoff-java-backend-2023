package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

interface DateParserHandler {
    Optional<LocalDate> handleRequest(String string);
}
