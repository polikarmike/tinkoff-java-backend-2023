package edu.hw7.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

class PersonDatabaseTest {

    @Test
    @DisplayName("Функциональная проверка CachedPersonDatabase")
    public void testCachedPersonDatabase() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CachedPersonDatabase database = new CachedPersonDatabase();

        for (int i = 0; i < threadCount; i++) {
            final int personId = i;
            executorService.execute(() -> {
                Person person = new Person(personId,
                    "Name" + personId,
                    "Address" + personId,
                    "Phone" + personId);
                database.add(person);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int personId = i;
            executorService.execute(() -> {
                List<Person> byName = database.findByName("Name" + personId);
                assertEquals(1, byName.size());

                List<Person> byAddress = database.findByAddress("Address" + personId);
                assertEquals(1, byAddress.size());

                List<Person> byPhone = database.findByPhone("Phone" + personId);
                assertEquals(1, byPhone.size());
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int personId = i;
            executorService.execute(() -> database.delete(personId));
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(0, database.findByName("Name0").size());
        assertEquals(0, database.findByAddress("Address0").size());
        assertEquals(0, database.findByPhone("Phone0").size());
    }

    @Test
    @DisplayName("Функциональная проверка SynchronizedPersonDatabase")
    public void testSynchronizedPersonDatabase() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        SynchronizedPersonDatabase database = new SynchronizedPersonDatabase();

        for (int i = 0; i < threadCount; i++) {
            final int personId = i;
            executorService.execute(() -> {
                Person person = new Person(personId,
                    "Name" + personId,
                    "Address" + personId,
                    "Phone" + personId);

                database.add(person);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int personId = i;
            executorService.execute(() -> {
                List<Person> byName = database.findByName("Name" + personId);
                assertEquals(1, byName.size());

                List<Person> byAddress = database.findByAddress("Address" + personId);
                assertEquals(1, byAddress.size());

                List<Person> byPhone = database.findByPhone("Phone" + personId);
                assertEquals(1, byPhone.size());
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int personId = i;
            executorService.execute(() -> database.delete(personId));
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(0, database.findByName("Name0").size());
        assertEquals(0, database.findByAddress("Address0").size());
        assertEquals(0, database.findByPhone("Phone0").size());
    }
}



