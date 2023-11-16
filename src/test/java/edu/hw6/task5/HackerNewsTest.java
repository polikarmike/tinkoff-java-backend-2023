package edu.hw6.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HackerNewsTest {

    @Test
    @DisplayName("Метод hackerNewsTopStories возвращает непустой массив")
    void testHackerNewsTopStories() {
        // Given
        HackerNews hackerNews = new HackerNews();

        // When
        long[] topStories = hackerNews.hackerNewsTopStories();

        // Then
        assertNotNull(topStories);
        assertTrue(topStories.length > 0);
    }

    @Test
    @DisplayName("Метод news возвращает заголовок для допустимого ID")
    void testReturnTitleForValidId() {
        // Given
        HackerNews hackerNews = new HackerNews();
        long validId = 37570037;

        // When
        String title = hackerNews.news(validId);

        // Then
        assertNotNull(title);
        assertEquals("JDK 21 Release Notes", title);
    }

    @Test
    @DisplayName("Метод news возвращает стандартное сообщение для недопустимого ID")
    void testMessageForInvalidId() {
        // Given
        HackerNews hackerNews = new HackerNews();
        long invalidId = -1;

        // When
        String title = hackerNews.news(invalidId);

        // Then
        assertEquals("Title not found", title);
    }
}

