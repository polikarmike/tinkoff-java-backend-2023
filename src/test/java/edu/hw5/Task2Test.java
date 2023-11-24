package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {

    @Test
    @DisplayName("Поиск пятниц 13-го в году")
    void testFindFridaysThe13th() {
        // Given
        int year = 2023;

        // When
        List<LocalDate> result = Task2.findFridaysThe13th(year);

        // Then
        assertEquals(2, result.size());
        assertEquals(LocalDate.of(2023, Month.JANUARY, 13), result.get(0));
        assertEquals(LocalDate.of(2023, Month.OCTOBER, 13), result.get(1));
    }

    @Test
    @DisplayName("Поиск следующей пятницы 13-го")
    void testFindNextFridayThe13th() {
        // Given
        LocalDate currentDate = LocalDate.of(2023, Month.JANUARY, 1);

        // When
        LocalDate result = Task2.findNextFridayThe13th(currentDate);

        // Then
        assertEquals(LocalDate.of(2023, Month.JANUARY, 13), result);
    }
}

