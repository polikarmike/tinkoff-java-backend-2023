package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    private final char placeholder = '_';

    private final String keepGuessingMessage = "Keep guessing!";
    private final String outOfAttemptsMessage = "You're out of attempts! The word was: ";
    private final String winMessage = "Congratulations, you've won!";
    private final String defeatMessage = "You gave up! The word was: ";

    Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        Arrays.fill(this.userAnswer, placeholder);
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public GuessResult guessResult() {
        return new GuessResult.SuccessfulGuess(userAnswer.clone(), attempts, maxAttempts, keepGuessingMessage);
    }

    @NotNull
    public GuessResult guess(char guess) {
        boolean correctGuess = false;
        boolean alreadyGuessed = false;

        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                if (userAnswer[i] == placeholder) {
                    userAnswer[i] = guess;
                    correctGuess = true;
                } else {
                    alreadyGuessed = true;
                }
            }
        }

        if (alreadyGuessed) {
            return new GuessResult.SuccessfulGuess(userAnswer.clone(), attempts, maxAttempts, keepGuessingMessage);
        }

        if (!correctGuess) {
            attempts++;
        }

        if (String.valueOf(userAnswer).equals(answer)) {
            return new GuessResult.Win(userAnswer.clone(), attempts, maxAttempts, winMessage);
        }

        if (attempts >= maxAttempts) {
            return new GuessResult.Defeat(userAnswer.clone(), attempts, maxAttempts, outOfAttemptsMessage + answer);
        }

        return new GuessResult.SuccessfulGuess(userAnswer.clone(), attempts, maxAttempts, keepGuessingMessage);
    }


    @NotNull
    public GuessResult giveUp() {
        return new GuessResult.Defeat(userAnswer.clone(), maxAttempts, maxAttempts, defeatMessage + answer);
    }
}
