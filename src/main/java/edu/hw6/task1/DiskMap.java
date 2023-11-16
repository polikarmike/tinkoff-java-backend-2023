package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DiskMap implements Map<String, String> {
    private String filePath;
    private Map<String, String> inMemoryMap;

    public DiskMap(String filePath) {
        this.filePath = filePath;
        this.inMemoryMap = new HashMap<>();
        loadFromDisk();
    }

    private void loadFromDisk() {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    inMemoryMap.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToDisk() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : inMemoryMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public int size() {
        return inMemoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        String oldValue = inMemoryMap.put(key, value);
        saveToDisk();
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = inMemoryMap.remove(key);
        saveToDisk();
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        inMemoryMap.putAll(m);
        saveToDisk();
    }

    @Override
    public void clear() {
        inMemoryMap.clear();
        saveToDisk();
    }

    @Override
    public java.util.Set<String> keySet() {
        return inMemoryMap.keySet();
    }

    @Override
    public java.util.Collection<String> values() {
        return inMemoryMap.values();
    }

    @Override
    public java.util.Set<Map.Entry<String, String>> entrySet() {
        return inMemoryMap.entrySet();
    }
}
