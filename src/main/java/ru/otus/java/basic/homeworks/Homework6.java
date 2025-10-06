package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.Arrays;

public class Homework6 {
    public static void main(String[] args) {
        ArrayList<Cat> arrayCats = new ArrayList<>(Arrays.asList(new Cat("Barsik", 7),
                new Cat("Murka", 11),
                new Cat("Malish", 14)));
        Plates plate = new Plates(32);
        for (int i = 0; i < arrayCats.size(); i++) {
            arrayCats.get(i).eatFood(plate);
        }
        for (int i = 0; i < arrayCats.size(); i++) {
            arrayCats.get(i).info();
        }
    }
}
