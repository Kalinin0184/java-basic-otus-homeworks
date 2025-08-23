package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world");
        printLine(6, "Привет мир!");
        sumArr(3,4,5,6,7,8,9);
    }

    public static void printLine(int numberPrinting, String strPrint)
    {
        for (int i = 0; i < numberPrinting; i++) {
            System.out.println(strPrint);
        }
    }

    public static void sumArr(int... array)
    {
        int sumA = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 5)
            {
                sumA += array[i];
            }
        }
        System.out.println(sumA);
    }
}
