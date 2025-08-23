package ru.otus.java.basic.homeworks;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world");
        printLine(6, "Привет мир!");
        sumArr(new int[]{3, 4, 5, 6, 7, 8, 9});
        int[] arrayFill = new int[10];
        fillArr(4, arrayFill);
        int[] arrayIncrease = {5, 1, 34, 67, 24, 5625, 11};
        increaseArr(3, arrayIncrease);
        weighingArr(new int[]{10, 15, 20, 25, 30, 35, 40, 45});
        int[] summationArraysFirst = {1, 2, 3};
        int[] summationArraysSecond = {2, 2};
        int[] summationArraysThird = {1, 1, 1, 1, 1};
        summationArrays(summationArraysFirst, summationArraysSecond, summationArraysThird);
        int[] revArr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        reverseArray(revArr);
        int[] arrUpDown = {4, 3, 2, 1};
        checkingUpDown(arrUpDown);
    }

    public static void printLine(int numberPrinting, String strPrint) {
        for (int i = 0; i < numberPrinting; i++) {
            System.out.println(strPrint);
        }
    }

    public static void sumArr(int[] array) {
        int sumA = 0;
        for (int j : array) {
            if (j > 5) {
                sumA += j;
            }
        }
        System.out.println(sumA);
    }

    public static void fillArr(int variableNumber, int[] arrayFill) {
        Arrays.fill(arrayFill, variableNumber);
        System.out.println(Arrays.toString(arrayFill));
    }

    public static void increaseArr(int incNumber, int[] arrayIncrease) {
        for (int i = 0; i < arrayIncrease.length; i++) {
            arrayIncrease[i] += incNumber;
        }
        System.out.println(Arrays.toString(arrayIncrease));
    }

    public static void weighingArr(int[] arrWeighing) {
        int sumA = 0, sumB = 0;
        int variableHalfNumber = arrWeighing.length / 2;
        for (int i = 0; i < arrWeighing.length; i++) {
            if (i < variableHalfNumber) {
                sumA += arrWeighing[i];
            } else {
                sumB += arrWeighing[i];
            }
        }
        if (sumA > sumB) {
            System.out.println("Первая половина массива больше!");
        } else {
            System.out.println("Вторая половина массива больше!");
        }

    }

    public static void summationArrays(int[] summationArraysFirst, int[] summationArraysSecond, int[] summationArraysThird) {
        int a = summationArraysFirst.length;
        int b = summationArraysSecond.length;
        int c = summationArraysThird.length;
        int d = Math.max(a, b);
        int e = Math.max(c, d);
        int[] resultArray = new int[e];
        for (int i = 0; i < e; i++) {
            if (i < a) {
                resultArray[i] += summationArraysFirst[i];
            }
            if (i < b) {
                resultArray[i] += summationArraysSecond[i];
            }
            if (i < c) {
                resultArray[i] += summationArraysThird[i];
            }
        }
        System.out.println(Arrays.toString(resultArray));
    }

    public static void reverseArray(int[] arrRev) {
        int[] reverse = new int[arrRev.length];
        for (int i = 0; i < arrRev.length; i++) {
            reverse[i] = arrRev[arrRev.length - 1 - i];
        }
        System.out.println(Arrays.toString(reverse));
    }

    public static void checkingUpDown(int[] arrUpDown) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите как проверить массив: В порядке возрастания(1), В порядке убывания(2)");
        int number = scanner.nextInt();
        boolean result = false;
        if (number == 1) {
            for (int i = 0; i < arrUpDown.length - 1; i++) {
                if (arrUpDown[i] < arrUpDown[i + 1]) {
                    result = true;
                }
            }
            if (result) {
                System.out.println("Массив идет в порядке возрастания");
            } else {
                System.out.println("Массив не идет в порядке возрастания");
            }
        }
        if (number == 2) {
            result = true;
            for (int i = 0; i < arrUpDown.length - 1; i++) {
                if (arrUpDown[i] > arrUpDown[i + 1]) {
                    result = false;
                }
            }
            if (!result) {
                System.out.println("Массив идет в порядке убывания");
            } else {
                System.out.println("Массив не идет в порядке убывания");
            }
        }
        if (number != 1 && number != 2) {
            System.out.println("Выберите правильный вариант проверки");
        }
    }
}
