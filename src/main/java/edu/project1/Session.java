package edu.project1;

import java.util.HashSet;

class Session {
    private final String answer;
    private final StringBuilder userAnswer;
    private final int maxAttempts;
    private int attempts;
    private final HashSet<Character> guessedChars;

    private static final char PLACEHOLDER = '_';

    Session(String answer, int maxAttempts) {
        this.answer = answer.toLowerCase();
        this.userAnswer = initializeUserAnswer(answer.length());
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.guessedChars = new HashSet<>();
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public GuessResult initState() {
        return new GuessResult.InitState(userAnswer.toString(), attempts, maxAttempts);
    }

    public GuessResult guess(char guess) {
        if (guessedChars.contains(guess)) {
            return new GuessResult.AlreadyGuessed(userAnswer.toString(), attempts, maxAttempts);
        }

        guessedChars.add(guess);

        boolean correctGuess = processGuess(guess);

        if (!correctGuess) {
            attempts++;
        }

        if (userAnswer.toString().equals(answer)) {
            return new GuessResult.Win(answer, attempts, maxAttempts);
        }

        if (attempts >= maxAttempts) {
            return new GuessResult.Defeat(answer, attempts, maxAttempts);
        }

        return new GuessResult.SuccessfulGuess(userAnswer.toString(), attempts, maxAttempts);
    }

    public GuessResult giveUp() {
        return new GuessResult.GiveUp(answer, maxAttempts, maxAttempts);
    }

    private StringBuilder initializeUserAnswer(int length) {
        StringBuilder placeholder = new StringBuilder();
        placeholder.append(String.valueOf(PLACEHOLDER).repeat(Math.max(0, length)));
        return placeholder;
    }

    private boolean processGuess(char guess) {
        boolean correctGuess = false;

        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer.setCharAt(i, guess);
                correctGuess = true;
            }
        }
        return correctGuess;
    }
}
