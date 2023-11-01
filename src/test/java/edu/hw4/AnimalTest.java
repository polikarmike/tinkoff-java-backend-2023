package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private List<Animal> animals;
    private List<Animal> animalsExtraFish;

//    @BeforeEach
    void setUp() {
        // Given
        animals = Arrays.asList(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 9, 25, 5, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.F, 13, 30, 7, true),
            new Animal("Parrot1", Animal.Type.BIRD, Animal.Sex.M, 15, 15, 1, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 2, true),
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 2, 20, 4, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 5, 101, 8, true),
            new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 2, 10, 20, false),
            new Animal("Parrot2 Lucy Sue", Animal.Type.BIRD, Animal.Sex.F, 2, 18, 2, false),
            new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 0, true)
        );
    }

    void setUpExtraFish() {
        // Given
        animalsExtraFish = Arrays.asList(
            new Animal("Fish3", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Fish4", Animal.Type.FISH, Animal.Sex.F, 2, 100, 200, false)
        );
    }

    void setUpWithMistake() {
        // Given
        animals = Arrays.asList(
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 2, -6, -1, false),
            new Animal("Fish2", Animal.Type.FISH, Animal.Sex.M, -1, 3, 0, false),
            new Animal("", Animal.Type.FISH, Animal.Sex.M, 4, 3, 0, false),
            new Animal("Fish3", Animal.Type.FISH, null, 3, 2, 1, false),
            new Animal("Fish3", null, null, -3, 2, 1, false)
        );
    }

    @Test
    @DisplayName("Задание №1: Сортировка животных по росту")
    void testSortAnimalsByHeight() {
        //Given
        setUp();

        // When
        List<Animal> sortedAnimals = AnimalMethods.sortByHeight(animals);

        // Then
        for (int i = 1; i < sortedAnimals.size(); i++) {
            assertTrue(sortedAnimals.get(i - 1).height() <= sortedAnimals.get(i).height());
        }
    }

    @Test
    @DisplayName("Задание №2: Выбор топ K животных по весу")
    void testSortAndSelectTopKByWeight() {
        // Given
        setUp();
        int k = 2;

        // When
        List<Animal> topKAnimals = AnimalMethods.topKByWeight(animals, k);

        // Then
        assertEquals(k, topKAnimals.size());
        for (int i = 1; i < topKAnimals.size(); i++) {
            assertTrue(topKAnimals.get(i - 1).weight() >= topKAnimals.get(i).weight());
        }
    }

    @Test
    @DisplayName("Задание №3: Подсчет количества животных по типу")
    void testCountAnimalsByType() {
        //Given
        setUp();

        // When
        Map<Animal.Type, Integer> countByType = AnimalMethods.countByType(animals);

        // Then
        assertEquals(2, countByType.get(Animal.Type.CAT));
        assertEquals(2, countByType.get(Animal.Type.DOG));
        assertEquals(2, countByType.get(Animal.Type.BIRD));
        assertEquals(2, countByType.get(Animal.Type.FISH));
        assertEquals(2, countByType.get(Animal.Type.SPIDER));
    }

    @Test
    @DisplayName("Задание №4: Поиск животного с самым длинным именем")
    void testGetAnimalWithLongestName() {
        //Given
        setUp();

        // When
        Animal animalWithLongestName = AnimalMethods.getAnimalWithLongestName(animals);

        // Then
        assertEquals("Parrot2 Lucy Sue", animalWithLongestName.name());
    }

    @Test
    @DisplayName("Задание №5: Поиск доминирующего пола среди животных (равное количество)")
    void testGetDominantSexEqual() {
        //Given
        setUp();

        // When
        Animal.Sex dominantSex = AnimalMethods.getDominantSex(animals);

        // Then
        assertNull(dominantSex);
    }

    @Test
    @DisplayName("Задание №5: Поиск доминирующего пола среди животных (неравное количество №1)")
    void testGetDominantSexNotEqual1() {
        //Given
        setUpExtraFish();

        // When
        Animal.Sex dominantSex = AnimalMethods.getDominantSex(animalsExtraFish);

        // Then
        assertEquals(Animal.Sex.F, dominantSex);
    }

    @Test
    @DisplayName("Задание №5: Поиск доминирующего пола среди животных (неравное количество №2)")
    void testGetDominantSexNotEqual2() {
        //Given
        setUpWithMistake();

        // When
        Animal.Sex dominantSex = AnimalMethods.getDominantSex(animals);

        // Then
        assertEquals(Animal.Sex.M, dominantSex);
    }


    @Test
    @DisplayName("Задание №6: Поиск самых тяжелых животных по типу")
    void testFindHeaviestAnimalByType() {
        //Given
        setUp();

        // When
        Map<Animal.Type, Animal> heaviestByType = AnimalMethods.getHeaviestByType(animals);

        // Then
        assertEquals(8, heaviestByType.get(Animal.Type.DOG).weight());
        assertEquals(5, heaviestByType.get(Animal.Type.CAT).weight());
        assertEquals(2, heaviestByType.get(Animal.Type.BIRD).weight());
    }

    @Test
    @DisplayName("Задание №7: Поиск K-того по возрасту животного")
    void testFindKthOldestAnimal() {
        //Given
        setUp();
        int k = 2;

        // When
        Animal kthOldest = AnimalMethods.getKthOldestAnimal(animals, k);

        // Then
        assertEquals("Dog1", kthOldest.name());
    }

    @Test
    @DisplayName("Задание №8: Поиск самого тяжелого животного ниже заданной высоты")
    void testGetHeaviestAnimalBelowHeight() {
        // Given
        setUp();
        int k = 27;

        // When
        Optional<Animal> heaviestBelowHeight = AnimalMethods.getHeaviestAnimalBelowHeight(animals, k);

        // Then
        assertTrue(heaviestBelowHeight.isPresent());
        assertEquals("Fish2", heaviestBelowHeight.get().name());
    }

    @Test
    @DisplayName("Задание №9: Вычисление общего количества лап у животных")
    void testTotalPawsCount() {
        // Given
        setUp();

        // When
        int totalPaws = AnimalMethods.totalPawsCount(animals);

        // Then
        assertEquals(36, totalPaws);
    }

    @Test
    @DisplayName("Задание №10: Поиск животных с несоответствием возраста и лап")
    void testGetAgeMismatchPawsAnimals() {
        // Given
        setUp();

        // When
        List<Animal> animalsWithMismatchedAgeAndPaws = AnimalMethods.getAgeMismatchPawsAnimals(animals);

        // Then
        assertEquals(9, animalsWithMismatchedAgeAndPaws.size());
    }

    @Test
    @DisplayName("Задание №11: Поиск высоких агрессивных животных")
    void testGetTallBitingAnimals() {
        // Given
        setUp();

        // When
        List<Animal> tallBitingAnimals = AnimalMethods.getTallBitingAnimals(animals);

        // Then
        assertEquals(1, tallBitingAnimals.size());
    }

    @Test
    @DisplayName("Задание №12: Подсчет животных с весом больше роста")
    void testCountWeightGreaterThanHeight() {
        // Given
        setUp();

        // When
        int count = AnimalMethods.countWeightGreaterThanHeight(animals);

        // Then
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Задание №13: Поиск животных с именами, содержащими более двух слов")
    void testGetAnimalsWithComplexNames() {
        // Given
        setUp();

        // When
        List<Animal> AnimalsWithComplexNames = AnimalMethods.getAnimalsWithComplexNames(animals);

        // Then
        assertEquals("Parrot2 Lucy Sue", AnimalsWithComplexNames.get(0).name());
    }

    @Test
    @DisplayName("Задание №14: Проверка наличия собаки выше k")
    void testHasTallDog() {
        // Given
        setUp();

        // When
        int k = 20;
        boolean hasTallDog = AnimalMethods.hasTallDog(animals, k);

        // Then
        assertTrue(hasTallDog);
    }

    @Test
    @DisplayName("Задание №15: Вычисление общего веса животных в заданном возрастном интервале")
    void testTotalWeightBetweenAges() {
        // Given
        setUp();
        int k = 3;
        int l = 15;

        // When
        int totalWeight = AnimalMethods.totalWeightBetweenAges(animals, k, l);

        // Then
        assertEquals(21, totalWeight);
    }

    @Test
    @DisplayName("Задание №16: Cортировки по типу, полу и имени")
    void testSortByTypeSexName() {
        // Given
        setUp();

        // When
        List<Animal> sortedAnimals = AnimalMethods.sortByTypeSexName(animals);


        // Then
        assertEquals("Cat1", sortedAnimals.get(0).name());
        assertEquals("Parrot1", sortedAnimals.get(4).name());
        assertEquals("Spider2", sortedAnimals.get(9).name());
        }

    @Test
    @DisplayName("Задание №17: Проверка, кусаются ли пауки чаще, чем собаки")
    void testIsSpidersBiteMoreThanDogs() {
        // Given
        setUp();

        // When
        boolean result = AnimalMethods.isSpidersBiteMoreThanDogs(animals);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Задание №18: Поиск самой тяжелой рыбы")
    void testFindHeaviestFish() {
        // Given
        setUp();
        setUpExtraFish();

        // When
        Animal heaviestFish = AnimalMethods.findHeaviestFish(new ArrayList<>(List.of(animals, animalsExtraFish)));

        // Then
        assertEquals("Fish4", heaviestFish.name());
    }

    @Test
    @DisplayName("Задание №19: Валидация записей с ошибками")
    void testValidationWithErrors() {
        // Given
        setUpWithMistake();

        // When
        Map<String, Set<ValidationError>> errorMap = AnimalMethods.validateAnimals(animals);

        // Then
        assertEquals(4, errorMap.size());
        Set<ValidationError> Fish1Errors = errorMap.get("Fish1");
        assertEquals(2, Fish1Errors.size());
    }

    @Test
    @DisplayName("Задание №20: Получение полей с ошибками")
    void testGetErrors() {
        // Given
        setUpWithMistake();

        // When
        Map<String, String> errorMap = AnimalMethods.getValidationErrors(animals);

        // Then
        assertEquals(4, errorMap.size());
        String Fish1Errors = errorMap.get("Fish1");
        assertTrue(Fish1Errors.contains("Height"));
        assertTrue(Fish1Errors.contains("Weight"));

    }

}
