package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void add(List<T> fruits) {
        this.fruits.addAll(fruits);
    }

    public float weight() {
        float totalWeight = 0.0f;
        for (T fruit : fruits) {
            totalWeight += fruit.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<?> otherBox) {
        return Math.abs(this.weight() - otherBox.weight()) < 0.0001f;
    }

    public void transferTo(Box<? super T> otherBox) {
        if (otherBox == null) {
            throw new IllegalArgumentException("Коробка назначения не может быть null");
        }
        otherBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

    public List<T> getFruits() {
        return new ArrayList<>(fruits);
    }

    public int size() {
        return fruits.size();
    }
}