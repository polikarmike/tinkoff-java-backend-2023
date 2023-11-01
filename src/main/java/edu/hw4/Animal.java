package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public static final int PAWS_FOR_CAT_AND_DOG = 4;
    public static final int PAWS_FOR_BIRD = 2;
    public static final int PAWS_FOR_FISH = 0;
    public static final int PAWS_FOR_SPIDER = 8;

    public int paws() {
        return switch (type) {
            case CAT, DOG -> PAWS_FOR_CAT_AND_DOG;
            case BIRD -> PAWS_FOR_BIRD;
            case FISH -> PAWS_FOR_FISH;
            case SPIDER -> PAWS_FOR_SPIDER;
        };
    }
}
