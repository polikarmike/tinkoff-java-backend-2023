package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        persons.put(person.id(), person);
        addToIndex(nameIndex, person.name(), person);
        addToIndex(addressIndex, person.address(), person);
        addToIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = persons.remove(id);
        removeFromIndex(nameIndex, person.name(), person);
        removeFromIndex(addressIndex, person.address(), person);
        removeFromIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameIndex.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressIndex.getOrDefault(address, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneIndex.getOrDefault(phone, new ArrayList<>());
    }

    private void addToIndex(Map<String, List<Person>> index, String key, Person person) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private void removeFromIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> personsList = index.get(key);
        if (personsList != null) {
            personsList.remove(person);
            if (personsList.isEmpty()) {
                index.remove(key);
            }
        }
    }
}

