package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            persons.put(person.id(), person);
            addToIndex(nameIndex, person.name(), person);
            addToIndex(addressIndex, person.address(), person);
            addToIndex(phoneIndex, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = persons.remove(id);
            removeFromIndex(nameIndex, person.name(), person);
            removeFromIndex(addressIndex, person.address(), person);
            removeFromIndex(phoneIndex, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return nameIndex.getOrDefault(name, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressIndex.getOrDefault(address, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneIndex.getOrDefault(phone, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
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
