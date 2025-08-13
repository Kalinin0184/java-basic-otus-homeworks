package ru.otus.java.basic.homeworks.homework3;

public class Homework3 {
    public static void main(String[] args) {
        greetings();
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0)
        {
            System.out.println("Сумма положительная");
        }
        else
        {
            System.out.println("Сумма отрицательная");
        }
    }
}
