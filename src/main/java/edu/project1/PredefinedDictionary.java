package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

class PredefinedDictionary {
    private final String[] words;
    private final Random random;
    private final String[] exampleWords =
        {"apple", "banana", "cherry", "grape", "lemon", "orange", "strawberry", "watermelon"};

    PredefinedDictionary() {
        this.words = exampleWords;
        this.random = new Random();
    }

    public @NotNull String randomWord() {
        int randomIndex = random.nextInt(words.length);
        return words[randomIndex];
    }
}
