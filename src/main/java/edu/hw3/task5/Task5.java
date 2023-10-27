package edu.hw3.task5;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    private Task5() {}

    public static List<Contact> parseContacts(String[] names, String sortOrder) {
        if (names == null) {
            return new ArrayList<>();
        }

        List<Contact> contacts = new ArrayList<>();
        for (String name : names) {
            contacts.add(new Contact(name));
        }

        if (sortOrder.equalsIgnoreCase("ASC")) {
            contacts.sort(Comparator.comparing(Contact::getLastName));
        } else if (sortOrder.equalsIgnoreCase("DESC")) {
            contacts.sort(Comparator.comparing(Contact::getLastName, Collections.reverseOrder()));
        }

        return contacts;
    }
}
