package ru.otus.java.basic.homeworks;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world");
        printLine(6, "Привет мир!");
        sumArr(new int[] {3,4,5,6,7,8,9});
        int[] arrayFill = new int[10];
        fillArr(4, arrayFill);
        int[] arrayIncrease = {5,1,34,67,24,5625,11};
        increaseArr(3, arrayIncrease);
        weighingArr(new int[] {10, 15, 20, 25, 30, 35, 40, 45});
    }

    public static void printLine(int numberPrinting, String strPrint)
    {
        for (int i = 0; i < numberPrinting; i++) {
            System.out.println(strPrint);
        }
    }

    public static void sumArr(int[] array)
    {
        int sumA = 0;
        for (int j : array) {
            if (j > 5) {
                sumA += j;
            }
        }
        System.out.println(sumA);
    }

    public static void fillArr(int variableNumber, int[] arrayFill)
    {
        Arrays.fill(arrayFill, variableNumber);
        System.out.println(Arrays.toString(arrayFill));
    }

    public static void increaseArr(int incNumber, int[] arrayIncrease)
    {
        for (int i = 0; i < arrayIncrease.length; i++) {
            arrayIncrease[i] += incNumber;
        }
        System.out.println(Arrays.toString(arrayIncrease));
    }

    public static void weighingArr(int[] arrWeighing)
    {
        int sumA = 0, sumB = 0;
        int variableHalfNumber = arrWeighing.length / 2;
        for (int i = 0; i < arrWeighing.length; i++) {
            if (i < variableHalfNumber)
            {
                sumA += arrWeighing[i];
            }
            else {
                sumB += arrWeighing[i];
            }
        }
        if (sumA > sumB)
        {
            System.out.println("Первая половина массива больше!");
        }
        else {
            System.out.println("Вторая половина массива больше!");
        }

    }
}
