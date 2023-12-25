package edu.hw6.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HackerNewsTest {

    @Test
    @DisplayName("Метод hackerNewsTopStories возвращает массив ID")
    void testHackerNewsTopStories() throws Exception {
        // Given
        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        HackerNews hackerNews = new HackerNews(mockHttpClient);

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockResponse);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("[1,2,3]");

        // When
        long[] result = hackerNews.hackerNewsTopStories();

        // Then
        assertArrayEquals(new long[]{1, 2, 3}, result);
    }

    @Test
    @DisplayName("Метод news возвращает заголовок для допустимого ID")
    void testReturnTitleForValidId() throws IOException, InterruptedException {
        // Given
        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        HackerNews hackerNews = new HackerNews(mockHttpClient);

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockResponse);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("{\"title\":\"Test Title\"}");

        // When
        String result = hackerNews.news(123);

        // Then
        assertEquals("Test Title", result);
    }

    @Test
    @DisplayName("Метод news возвращает стандартное сообщение для недопустимого ID")
    void testMessageForInvalidId() throws IOException, InterruptedException {
        // Given
        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        HackerNews hackerNews = new HackerNews(mockHttpClient);
        long invalidId = -1;

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn("");
        when(mockResponse.statusCode()).thenReturn(200);

        // When
        String title = hackerNews.news(invalidId);

        // Then
        assertEquals("Title not found", title);
    }
}
