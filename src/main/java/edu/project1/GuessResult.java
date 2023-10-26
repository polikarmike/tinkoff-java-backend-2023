package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {
    String state();

    int attempt();

    int maxAttempts();

    @NotNull String message();


    record InitState(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Please enter a letter.";
        }
    }

    record AlreadyGuessed(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "This letter was already guessed!";
        }
    }


    record Defeat(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You lost the game!";
        }
    }

    record GiveUp(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You gave up!";
        }
    }

    record Win(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Congratulations, you've won!";
        }
    }

    record SuccessfulGuess(String state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Keep guessing!";
        }
    }


}
