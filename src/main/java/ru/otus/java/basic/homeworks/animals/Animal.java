package ru.otus.java.basic.homeworks.animals;

public abstract class Animal {
    String name;
    double speedRun;
    double speedSwim;
    int endurance;

    public Animal(String name, double speedRun, double speedSwim, int endurance) {
        this.name = name;
        this.speedRun = speedRun;
        this.speedSwim = speedSwim;
        this.endurance = endurance;
    }

    public double run(int distance) {
        if (endurance > 0 && endurance >= distance) {
            endurance -= distance;
            System.out.print("Затраченное время: ");
            return distance / speedRun;
        } else {
            System.out.print("Животное устало ");
            return -1;
        }
    }

    public abstract double swim(int distance);

    public void info() {
        System.out.println("Животное: " + name + "имеет единиц выносливости: " + endurance);
    }
}
