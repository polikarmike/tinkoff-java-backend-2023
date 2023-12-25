package edu.hw3.task5;

public class Contact {
    String fullName;

    public Contact(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        String[] nameParts = fullName.split(" ");
        return nameParts.length > 1 ? nameParts[nameParts.length - 1] : nameParts[0];
    }

    @Override
    public String toString() {
        return fullName;
    }
}
