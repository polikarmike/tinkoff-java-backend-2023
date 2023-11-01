package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class AnimalValidator {

    private AnimalValidator() {

    }

    public static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.name().isBlank()) {
            errors.add(new ValidationError("Name", "Name cannot be blank"));
        }

        if (animal.type() == null) {
            errors.add(new ValidationError("Type", "Type cannot be null"));
        }

        if (animal.sex() == null) {
            errors.add(new ValidationError("Sex", "Sex cannot be null"));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError("Age", "Age cannot be negative"));
        }

        if (animal.height() < 0) {
            errors.add(new ValidationError("Height", "Height cannot be negative"));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("Weight", "Weight cannot be negative"));
        }

        return errors;
    }

}
