package ru.otus.java.basic.homeworks.homework3;

import java.util.Arrays;

public class Homework3 {
    public static void main(String[] args) {
        int[][] array = {{-1, -2, -3}, {1, 1, 1}, {3, 3, 3}};
        sumOfPositiveElements(array);
        drawSquare(6);
        int[][] arrayZero = {{3, 4, 5}, {1, 6, 7}, {4, 4, 4}};
        zeroingArray(arrayZero);
        int[][] arrayFind = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        findMax(arrayFind);
    }

    public static void sumOfPositiveElements(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > 0) {
                    sum += array[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    public static void drawSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void zeroingArray(int[][] arrayZero) {
        for (int i = 0; i < arrayZero.length; i++) {
            arrayZero[i][i] = 0;
            for (int j = 0; j < arrayZero.length; j++) {
                System.out.print(" " + arrayZero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void findMax(int[][] arrayFind) {
        int maxFind = 0;
        for (int i = 0; i < arrayFind.length; i++) {
            for (int j = 0; j < arrayFind.length; j++) {
                if (arrayFind[i][j] > maxFind) {
                    maxFind = arrayFind[i][j];
                }
            }
        }
        System.out.println(maxFind);
    }
}
