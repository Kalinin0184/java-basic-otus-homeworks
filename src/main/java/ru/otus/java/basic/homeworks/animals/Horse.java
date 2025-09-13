package ru.otus.java.basic.homeworks.animals;

public class Horse extends Animal {
    public Horse(String name, double speedRun, double speedSwim, int endurance) {
        super(name, speedRun, speedSwim, endurance);
    }

    public double swim(int distance) {
        if (endurance > 0 && endurance >= (distance * 4)) {
            endurance -= (distance * 4);
            System.out.print("Затраченное время: ");
            return distance / speedSwim;
        } else {
            System.out.print("Животное устало ");
            return -1;
        }
    }
}
