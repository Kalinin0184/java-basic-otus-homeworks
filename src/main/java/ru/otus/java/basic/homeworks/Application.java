package ru.otus.java.basic.homeworks;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов Иван Иванович", "+7-123-456-78-90");
        phoneBook.add("Петров Петр Петрович", "+7-234-567-89-01");
        phoneBook.add("Иванов Иван Иванович", "+7-999-888-77-66"); // Два телефона у одного человека
        phoneBook.add("Сидоров Сидор Сидорович", "+7-345-678-90-12");

        System.out.println("Телефоны Иванова:");
        Set<String> ivanovPhones = phoneBook.find("Иванов Иван Иванович");
        for (String phone : ivanovPhones) {
            System.out.println("  " + phone);
        }

        String phoneToCheck = "+7-234-567-89-01";
        System.out.println("\nТелефон " + phoneToCheck + " существует: " +
                phoneBook.containsPhoneNumber(phoneToCheck));

        System.out.println("\n" + phoneBook.toString());
    }
}
