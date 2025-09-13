package ru.otus.java.basic.homeworks;

import ru.otus.java.basic.homeworks.animals.*;

public class Homework5 {
    public static void main(String[] args) {
        Cat cat = new Cat("barsik", 2, 2, 10);
        Dog dog = new Dog("Sharik", 1, 2, 13);
        Horse horse = new Horse("Whish", 3, 1, 25);
        cat.info();
        dog.info();
        horse.info();

        System.out.println(cat.run(5));
        System.out.println(dog.run(5));
        System.out.println(horse.run(5));

        cat.info();
        dog.info();
        horse.info();

        System.out.println(cat.swim(4));
        System.out.println(dog.swim(4));
        System.out.println(horse.swim(5));


    }
}