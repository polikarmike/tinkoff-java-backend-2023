package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    private final DateParserHandler chain;

    public DateParser() {
        this.chain = buildChain();
    }

    private DateParserHandler buildChain() {
        return new YearMonthDayHandler(
            new DayMonthYearHandler(
                new DayMonthYearLongHandler(
                    new TomorrowHandler(
                        new TodayHandler(
                            new YesterdayHandler(
                                new AgoHandler(new EndHandler())))))));
    }

    public Optional<LocalDate> parseDate(String string) {
        return chain.handleRequest(string);
    }
}

