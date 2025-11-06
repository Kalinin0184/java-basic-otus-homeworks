package ru.otus.java.basic.homeworks;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String name, String phoneNumber) {
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
    }

    public Set<String> find(String name) {
        return phoneBook.getOrDefault(name, new HashSet<>());
    }

    public boolean containsPhoneNumber(String phoneNumber) {
        for (Set<String> phones : phoneBook.values()) {
            if (phones.contains(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PhoneBook:\n");
        for (Map.Entry<String, Set<String>> entry : phoneBook.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
