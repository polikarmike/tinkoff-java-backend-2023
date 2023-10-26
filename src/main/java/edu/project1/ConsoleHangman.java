package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final Scanner scanner = new Scanner(System.in);
    final static Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 6;

    public void run() {
        PredefinedDictionary dictionary = new PredefinedDictionary();
        boolean playAgain = true;

        TextHandler.printInitInfo();

        while (playAgain) {
            String wordToGuess = dictionary.randomWord();
            Session session = new Session(wordToGuess, MAX_ATTEMPTS);

            TextHandler.printStartSession();
            TextHandler.printState(session.initState());

            while (session.getAttempts() < session.getMaxAttempts()) {

                String userInput = readUserInput();

                if (userInput.equals(TextHandler.GIVE_UP_COMMAND)) {
                    GuessResult giveUpResult = session.giveUp();
                    TextHandler.printState(giveUpResult);
                    break;
                }

                char guess = userInput.charAt(0);

                GuessResult guessResult = session.guess(guess);
                TextHandler.printState(guessResult);

                if (guessResult instanceof GuessResult.Win || guessResult instanceof GuessResult.Defeat) {
                    break;
                }
            }
            playAgain = requestNewGame();

        }
    }

    private String readUserInput() {
        LOGGER.info(TextHandler.GUESS_PROMPT_MESSAGE);

        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase(TextHandler.GIVE_UP_COMMAND)) {
            return TextHandler.GIVE_UP_COMMAND;
        }

        if (userInput.length() != 1) {
            LOGGER.info(TextHandler.INVALID_LETTER_MESSAGE);
            return readUserInput();
        }

        return userInput.toLowerCase();
    }

    private String readPlayAgainInput() {
        LOGGER.info(TextHandler.EXIT_PROMPT_MESSAGE);

        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase(TextHandler.ACCEPTANCE_COMMAND)
            || userInput.equalsIgnoreCase(TextHandler.REJECTION_COMMAND)) {
            return userInput;
        }

        LOGGER.info(TextHandler.INVALID_EXIT_COMMAND_MESSAGE);
        return readPlayAgainInput();
    }

    private boolean requestNewGame() {
        String playAgainInput = readPlayAgainInput();
        return playAgainInput.equalsIgnoreCase(TextHandler.ACCEPTANCE_COMMAND);
    }
}
