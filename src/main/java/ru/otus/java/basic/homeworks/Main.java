package ru.otus.java.basic.homeworks;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());

        appleBox2.add(new Apple());
        appleBox2.add(new Apple());

        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        System.out.println("Вес коробки с яблоками 1: " + appleBox1.weight());
        System.out.println("Вес коробки с яблоками 2: " + appleBox2.weight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.weight());

        System.out.println("Коробка яблок 1 и коробка апельсинов равны? " +
                appleBox1.compare(orangeBox));
        System.out.println("Коробка яблок 1 и коробка яблок 2 равны? " +
                appleBox1.compare(appleBox2));

        System.out.println("До пересыпания: яблок в box1 = " + appleBox1.size() +
                ", в box2 = " + appleBox2.size());
        appleBox1.transferTo(appleBox2);
        System.out.println("После пересыпания: яблок в box1 = " + appleBox1.size() +
                ", в box2 = " + appleBox2.size());
    }
}