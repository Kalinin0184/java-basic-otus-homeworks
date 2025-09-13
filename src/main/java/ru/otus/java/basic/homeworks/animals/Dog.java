package ru.otus.java.basic.homeworks.animals;

public class Dog extends Animal {
    public Dog(String name, double speedRun, double speedSwim, int endurance) {
        super(name, speedRun, speedSwim, endurance);
    }

    public double swim(int distance) {
        if (endurance > 0 && endurance >= (distance * 2)) {
            endurance -= (distance * 2);
            System.out.print("Затраченное время: ");
            return distance / speedSwim;
        } else {
            System.out.print("Животное устало ");
            return -1;
        }
    }
}
