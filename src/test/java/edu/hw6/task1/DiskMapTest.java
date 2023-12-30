package edu.hw6.task1;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {

    private void deleteFile(DiskMap diskMap) {
        // Логика удаления файла
        File file = new File(diskMap.getFilePath());
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("Добавление значения в пустой DiskMap")
    void testPutValueInEmptyDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());

        // When
        diskMap.put("key1", "value1");

        // Then
        assertTrue(diskMap.containsKey("key1"));
        assertEquals("value1", diskMap.get("key1"));
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Удаление ключа из DiskMap")
    void testRemoveKeyFromDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        diskMap.remove("key1");

        // Then
        assertFalse(diskMap.containsKey("key1"));
        assertNull(diskMap.get("key1"));
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Очистка DiskMap")
    void testClearDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        diskMap.clear();

        // Then
        assertTrue(diskMap.isEmpty());
        assertEquals(0, diskMap.size());
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Добавление всех сразу значений в DiskMap")
    void testPutAllValuesInDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        Map<String, String> newValues = new HashMap<>();
        newValues.put("key3", "value3");
        newValues.put("key4", "value4");

        // When
        diskMap.putAll(newValues);

        // Then
        assertTrue(diskMap.containsKey("key3"));
        assertEquals("value3", diskMap.get("key3"));
        assertTrue(diskMap.containsKey("key4"));
        assertEquals("value4", diskMap.get("key4"));
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Добавление существующего ключа и возврат старого значения")
    void testPutExistingKeyReturnsOldValue() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");

        // When
        String oldValue = diskMap.put("key1", "newValue");

        // Then
        assertEquals("value1", oldValue);
        assertEquals("newValue", diskMap.get("key1"));
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Получение размера DiskMap")
    void testGetSizeOfDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        int size = diskMap.size();

        // Then
        assertEquals(2, size);
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Получение набора ключей DiskMap")
    void testGetKeySetOfDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        java.util.Set<String> keySet = diskMap.keySet();

        // Then
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
        assertEquals(2, keySet.size());
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Получение коллекции значений DiskMap")
    void testGetValuesCollectionOfDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        java.util.Collection<String> values = diskMap.values();

        // Then
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertEquals(2, values.size());
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Получение набора записей DiskMap")
    void testGetEntrySetOfDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        java.util.Set<Map.Entry<String, String>> entrySet = diskMap.entrySet();

        // Then
        assertTrue(entrySet.contains(Map.entry("key1", "value1")));
        assertTrue(entrySet.contains(Map.entry("key2", "value2")));
        assertEquals(2, entrySet.size());
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Проверка наличия значения в DiskMap")
    void testDiskMapContainsValue() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        boolean containsValue = diskMap.containsValue("value1");

        // Then
        assertTrue(containsValue);
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Проверка загрузки существующего DiskMap")
    void testLoadExistedDiskMap() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        DiskMap existedDiskMap = new DiskMap(tempFile.toString());
        boolean containsValue = existedDiskMap.containsValue("value1");

        // Then
        assertTrue(containsValue);
        deleteFile(diskMap);
    }

    @Test
    @DisplayName("Проверка загрузки несуществующего DiskMap")
    public void testFileNotFound() {
        // Given
        String filePath = "NonExistentPath/NonExistentFile.txt";

        // When & Then
        assertThrows(RuntimeException.class, () -> new DiskMap(filePath));

    }

    @Test
    @DisplayName("Проверка добавления значений в DiskMap с атрибутом 'только для чтения'")
    void testIOExceptionDuringPut() throws IOException {
        // Given
        Path tempDir = Files.createTempDirectory("tempDir");
        Path tempFile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(tempFile.toString());
        tempFile.toFile().setReadOnly();

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            diskMap.put("key", "value");
        });
    }
}
