package edu.project1;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class ConsoleHangmanTest {
    private List<String> logMessages;
    private Appender appender;

    @BeforeEach
    public void setUp() {
        logMessages = new ArrayList<>();
        appender = new AbstractAppender("ListAppender", null, null) {
            @Override
            public void append(LogEvent event) {
                logMessages.add(event.getMessage().getFormattedMessage());
            }
        };
        appender.start();
        Logger coreLogger = (Logger) LogManager.getRootLogger();
        coreLogger.addAppender(appender);
    }

    @AfterEach
    public void tearDown() {
        Logger coreLogger = (Logger) LogManager.getRootLogger();
        coreLogger.removeAppender(appender);
        appender.stop();
    }

    @Test
    @DisplayName("Проверка поражения")

    public void testDefeat() {
        // given
        String input = "f\ni\nj\nu\nv\nx\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // When
        ConsoleHangman hangman = new ConsoleHangman();
        hangman.run();

        // Then
        boolean containsDefeatMessage = logMessages.stream().anyMatch(message -> message.contains("You're out of attempts!"));
        assertThat(containsDefeatMessage).isTrue();
    }

    @Test
    @DisplayName("Проверка сдачи")
    public void testGiveUp() {
        // given
        String input = "GIVE UP\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // When
        ConsoleHangman hangman = new ConsoleHangman();
        hangman.run();

        // Then
        boolean containsGiveUp = logMessages.stream().anyMatch(message -> message.contains("You gave up!"));
        assertThat(containsGiveUp).isTrue();
    }
}
