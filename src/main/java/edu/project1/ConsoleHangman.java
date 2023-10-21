package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final Scanner scanner = new Scanner(System.in);
    final static Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 6;

    private static final String WELCOME_MESSAGE = "Welcome to Hangman!";
    private static final String ATTEMPTS_MESSAGE = "You have %d attempts to guess the word.";
    private static final String BEGIN_MESSAGE = "Let's begin!";
    private static final String EMPTY_LINE = "";

    private static final String EXIT_MESSAGE = "Do you want to play again? (yes/no)";
    private static final String GIVE_UP = "GIVE UP";
    private static final String GIVE_UP_INFO  = "To terminate the game, input: give up";
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final String ENTER_GUESS = "Enter your guess: ";
    private static final String PLEASE_ENTER_LETTER = "Please enter a single letter as your guess.";
    private static final String PLEASE_ENTER_YES_OR_NO = "Please enter 'yes' to play again or 'no' to quit.";

    public void run() {
        PredefinedDictionary dictionary = new PredefinedDictionary();
        boolean playAgain = true;

        while (playAgain) {
            String wordToGuess = dictionary.randomWord();
            Session session = new Session(wordToGuess, MAX_ATTEMPTS);

            LOGGER.info(WELCOME_MESSAGE);
            LOGGER.info(String.format(ATTEMPTS_MESSAGE, session.getMaxAttempts()));
            LOGGER.info(GIVE_UP_INFO);
            LOGGER.info(BEGIN_MESSAGE);
            LOGGER.info(EMPTY_LINE);

            while (session.getAttempts() < session.getMaxAttempts()) {
                printState(session.guessResult());

                String userInput = readUserInput();

                if (userInput.equals(EXIT_MESSAGE)) {
                    GuessResult giveUpResult = session.giveUp();
                    printState(giveUpResult);
                    break;
                }
                char guess = userInput.charAt(0);

                GuessResult guessResult = session.guess(guess);

                if (guessResult instanceof GuessResult.Win || guessResult instanceof GuessResult.Defeat) {
                    printState(guessResult);
                    break;
                }
            }

            String playAgainInput = readPlayAgainInput();
            if (!playAgainInput.equalsIgnoreCase(YES)) {
                playAgain = false;
            }
        }
    }

    private void printState(GuessResult guessResult) {
        LOGGER.info("Word: " + String.valueOf(guessResult.state()));
        LOGGER.info("Attempts left: " + (guessResult.maxAttempts() - guessResult.attempt()));
        LOGGER.info("Message: " + guessResult.message());
        LOGGER.info(EMPTY_LINE);
    }

    private String readUserInput() {
        LOGGER.info(ENTER_GUESS);

        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase(GIVE_UP)) {
            return EXIT_MESSAGE;
        }

        if (userInput.length() != 1) {
            LOGGER.info(PLEASE_ENTER_LETTER);
            return readUserInput();
        }

        return userInput.toLowerCase();
    }

    private String readPlayAgainInput() {
        LOGGER.info(EXIT_MESSAGE);

        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase(YES) || userInput.equalsIgnoreCase(NO)) {
            return userInput;
        }

        LOGGER.info(PLEASE_ENTER_YES_OR_NO);
        return readPlayAgainInput();
    }
}
