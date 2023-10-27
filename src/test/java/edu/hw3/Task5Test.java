package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    @DisplayName("Сортировка фамилий по возрастанию")
    public void SortAscendingTest() {
        // Given
        String[] names = { "John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes" };

        // When
        List<Contact> contacts = Task5.parseContacts(names, "ASC");

        // Then
        assertEquals("Thomas Aquinas", contacts.get(0).toString());
        assertEquals("Rene Descartes", contacts.get(1).toString());
        assertEquals("David Hume", contacts.get(2).toString());
        assertEquals("John Locke", contacts.get(3).toString());
    }

    @Test
    @DisplayName("Сортировка фамилий по убыванию")
    public void SortDescendingTest() {
        // Given
        String[] names = { "Paul Erdos", "Leonhard Euler", "Carl Gauss" };

        // When
        List<Contact> contacts = Task5.parseContacts(names, "DESC");

        // Then
        assertEquals("Carl Gauss", contacts.get(0).toString());
        assertEquals("Leonhard Euler", contacts.get(1).toString());
        assertEquals("Paul Erdos", contacts.get(2).toString());
    }

    @Test
    @DisplayName("Пустой входной массив")
    public void EmptyListTest() {
        // Given
        String[] names = {};

        // When
        List<Contact> contacts = Task5.parseContacts(names, "DESC");

        // Then
        assertTrue(contacts.isEmpty());
    }

    @Test
    @DisplayName("Null вход")
    public void NullInputTest() {
        // Given
        String[] names = null;

        // When
        List<Contact> contacts = Task5.parseContacts(names, "DESC");

        // Then
        assertTrue(contacts.isEmpty());
    }
}
