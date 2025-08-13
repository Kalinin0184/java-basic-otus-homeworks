package ru.otus.java.basic.homeworks.homework3;

import java.util.Random;
import java.util.Scanner;

public class Homework3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dataEntry, a, b, c, initValue, delta;
        boolean increment;
        System.out.println("Введите число от 1 до 5:");
        dataEntry = scanner.nextInt();
        if (dataEntry >= 1 && dataEntry <= 5) {
            switch (dataEntry) {
                case 1:
                    greetings();
                    break;
                case 2:
                    a = (int) (Math.random() * 40 - 20);
                    b = (int) (Math.random() * 40 - 20);
                    c = (int) (Math.random() * 40 - 20);
                    checkSign(a, b, c);
                    break;
                case 3:
                    selectColor();
                    break;
                case 4:
                    compareNumbers();
                    break;
                case 5:
                    initValue = (int) (Math.random() * 50 + 1);
                    delta = (int) (Math.random() * 50 + 1);
                    increment = new Random().nextBoolean();
                    addOrSubtractAndPrint(initValue, delta, increment);
                    break;
            }
        } else {
            System.out.println("Error! Введите число из заданного диапозона");
        }
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void selectColor() {
        int data = (int) (Math.random() * 30 + 1);
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = (int) (Math.random() * (100 + 1));
        int b = (int) (Math.random() * (100 + 1));
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            initValue += delta;
            System.out.println(initValue);
        } else {
            initValue -= delta;
            System.out.println(initValue);
        }
    }
}
