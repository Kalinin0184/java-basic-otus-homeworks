package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    public static void printLine(int numberPrinting, String strPrint)
    {
        for (int i = 0; i < numberPrinting; i++) {
            System.out.println(strPrint);
        }
    }
}
