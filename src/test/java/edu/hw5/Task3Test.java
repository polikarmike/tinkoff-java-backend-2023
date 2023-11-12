package edu.hw5;

import edu.hw5.Task3.DateParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    private final DateParser dateParser = new DateParser();

    @Test
    @DisplayName("Парсинг строки с форматом 'yyyy-mm-dd'")
    void testYearMonthDayLongFormat() {
        // Given
        String dateString = "2022-01-02";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2022, 1, 2), result.get());
    }

    @Test
    @DisplayName("Парсинг строки с форматом 'yyyy-m-d'")
    void testYearMonthDayShortFormat() {
        // Given
        String dateString = "2022-4-5";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2022, 4, 5), result.get());
    }

    @Test
    @DisplayName("Парсинг строки с форматом 'd/m/yy'")
    void testDayMonthYearShortFormat() {
        // Given
        String dateString = "11/12/22";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2022, 12, 11), result.get());
    }

    @Test
    @DisplayName("Парсинг строки с форматом 'd/m/yyyy'")
    void testDayMonthYearLongtFormat() {
        // Given
        String dateString = "11/12/2022";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2022, 12, 11), result.get());
    }

    @Test
    @DisplayName("Парсинг строки 'tomorrow'")
    void testTomorrowFormat() {
        // Given
        String dateString = "tomorrow";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().plusDays(1), result.get());
    }

    @Test
    @DisplayName("Парсинг строки 'today'")
    void testTodayFormat() {
        // Given
        String dateString = "today";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now(), result.get());
    }

    @Test
    @DisplayName("Парсинг строки 'yesterday'")
    void testYesterdayFormat() {
        // Given
        String dateString = "yesterday";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().minusDays(1), result.get());
    }

    @Test
    @DisplayName("Парсинг строки '3 days ago'")
    void testAgoFormat() {
        // Given
        String dateString = "3 days ago";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().minusDays(3), result.get());
    }

    @Test
    @DisplayName("Парсинг строки с неизвестным форматом")
    void testUnknownFormat() {
        // Given
        String dateString = "unknown format";

        // When
        Optional<LocalDate> result = dateParser.parseDate(dateString);

        // Then
        assertTrue(result.isEmpty());
    }
}
