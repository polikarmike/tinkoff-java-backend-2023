package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;


public class AnimalMethods {
    public static final int MINIMUM_TALL_HEIGHT = 100;

    private AnimalMethods() {

    }

    // Задание №1
    static List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    // Задание №2
    static List<Animal> topKByWeight(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    // Задание №3
    static Map<Animal.Type, Integer> countByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    // Задание №4
    static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    // Задание №5
    static Animal.Sex getDominantSex(List<Animal> animals) {
        long maleCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long femaleCount = animals.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();

        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        } else if (femaleCount > maleCount) {
            return Animal.Sex.F;
        } else {
            return null;
        }
    }

    // Задание №6
    static Map<Animal.Type, Animal> getHeaviestByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    // Задание №7
    static Animal getKthOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    // Задание №8
    static Optional<Animal> getHeaviestAnimalBelowHeight(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    // Задание №9
    static int totalPawsCount(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    // Задание №10
    static List<Animal> getAgeMismatchPawsAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    // Задание №11
    static List<Animal> getTallBitingAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> (animal.bites() && animal.height() > MINIMUM_TALL_HEIGHT))
            .toList();
    }

    // Задание №12
    static int countWeightGreaterThanHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    // Задание №13
    static List<Animal> getAnimalsWithComplexNames(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    // Задание №14
    static boolean hasTallDog(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    // Задание №15
    static Map<Animal.Type, Integer> totalWeightBetweenAges(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    // Задание №16
    static List<Animal> sortByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    // Задание №17
    public static boolean isSpidersBiteMoreThanDogs(List<Animal> animals) {
        long spiderCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        long dogCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        return spiderCount > dogCount;
    }

    // Задание №18
    static Animal findHeaviestFish(List<List<Animal>> animalLists) {
        return animalLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    // Задание №19
    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                AnimalValidator::validateAnimal,
                (existing, replacement) -> {
                    existing.addAll(replacement);
                    return existing;
                }
            ));
    }

    // Задание №20
    public static Map<String, String> getValidationErrors(List<Animal> animals) {
        return validateAnimals(animals)
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(ValidationError::toString)
                    .collect(Collectors.joining(", "))
            ));
    }
}
