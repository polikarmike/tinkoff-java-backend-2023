package edu.hw8.task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuoteServerClientTest {

    private static QuoteServer quoteServer;
    private static Thread serverThread;
    private static QuoteClient quoteClient;
    private static Thread clientThread;

    @AfterAll
    public static void tearDown() {
        if (quoteServer != null) {
            quoteServer.stop();
        }

        if (quoteClient != null) {
            quoteClient.stop();
        }

    }

    @Test
    @DisplayName("Проверка выдачи цитаты для корректного слова")
    public void testValidWord() {
        // Given
        ByteArrayOutputStream serverOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(serverOutput));


        quoteServer = new QuoteServer();
        serverThread = new Thread(quoteServer::run);
        serverThread.start();


        quoteClient = new QuoteClient();
        clientThread = new Thread(quoteClient::run);
        clientThread.start();

        // When
        String userInput = "личности\nexit\n";
        System.setIn(new java.io.ByteArrayInputStream(userInput.getBytes()));

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Then
        String serverLogs = serverOutput.toString();
        System.out.println(serverLogs);

        assertTrue(serverLogs.contains("Не переходи на личности там, где их нет"));
    }

    @Test
    @DisplayName("Проверка выдачи цитаты для некорректного слова")
    public void testInvalidWord() {
        // Given
        ByteArrayOutputStream serverOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(serverOutput));

        quoteServer = new QuoteServer();
        serverThread = new Thread(quoteServer::run);
        serverThread.start();

        quoteClient = new QuoteClient();
        clientThread = new Thread(quoteClient::run);
        clientThread.start();

        // When
        String userInput = "судьба\nexit\n";
        System.setIn(new java.io.ByteArrayInputStream(userInput.getBytes()));

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Then
        String serverLogs = serverOutput.toString();
        System.out.println(serverLogs);

        assertTrue(serverLogs.contains("Не могу найти цитату по этому ключевому слову"));
    }
}

