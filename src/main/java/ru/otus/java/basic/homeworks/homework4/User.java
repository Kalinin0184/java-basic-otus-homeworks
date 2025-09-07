package ru.otus.java.basic.homeworks.homework4;

public class User {
    private String name;
    private String surname;
    private String patronymic;
    private String birthday;
    private String email;

    public User(String name, String surname, String patronymic, String birthday, String email) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.email = email;
    }

    public void info() {
        System.out.println("ФИО: " + surname + " " + name + " " + patronymic);
        System.out.println("Год рождения: " + birthday);
        System.out.println("email: " + email);
    }
}
