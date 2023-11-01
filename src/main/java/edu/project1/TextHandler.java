package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TextHandler {
    private TextHandler() {
    }

    final static Logger LOGGER = LogManager.getLogger();
    private static final String WELCOME_MESSAGE = "Welcome to Hangman!";
    private static final String GIVE_UP_INFO  = "To terminate the session, input: give up";
    private static final String BEGIN_MESSAGE = "Let's begin!";
    private static final String LINE_SEPARATOR = "";
    protected static final String GIVE_UP_COMMAND = "GIVE UP";
    protected static final String ACCEPTANCE_COMMAND = "YES";
    protected static final String REJECTION_COMMAND = "NO";
    protected static final String EXIT_PROMPT_MESSAGE = "Do you want to play again? (yes/no)";
    protected static final String GUESS_PROMPT_MESSAGE = "Enter your guess: ";
    protected static final String INVALID_LETTER_MESSAGE = "Please enter a single letter as your guess.";
    protected static final String INVALID_EXIT_COMMAND_MESSAGE = "Please enter 'yes' to play again or 'no' to quit.";

    protected static void printInitInfo() {
        LOGGER.info(WELCOME_MESSAGE);
        LOGGER.info(GIVE_UP_INFO);
    }

    protected static void printStartSession() {
        LOGGER.info(BEGIN_MESSAGE);
    }

    protected static void printState(GuessResult guessResult) {
        LOGGER.info("Word: " + guessResult.state());
        LOGGER.info("Attempts left: " + (guessResult.maxAttempts() - guessResult.attempt()));
        LOGGER.info("Message: " + guessResult.message());
        LOGGER.info(LINE_SEPARATOR);
    }


}

