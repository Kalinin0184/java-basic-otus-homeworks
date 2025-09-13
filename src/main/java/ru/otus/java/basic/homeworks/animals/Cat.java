package ru.otus.java.basic.homeworks.animals;

public class Cat extends Animal {
    public Cat(String name, double speedRun, double speedSwim, int endurance) {
        super(name, speedRun, speedSwim, endurance);
    }

    public double swim(int distance) {
        System.out.print("Кот плавать не умеет: ");
        return 0;
    }
}
