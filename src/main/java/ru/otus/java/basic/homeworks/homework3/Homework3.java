package ru.otus.java.basic.homeworks.homework3;

public class Homework3 {
    public static void main(String[] args) {
        int[][] array = {{-1,-2,-3}, {1,1,1}, {3,3,3}};
        sumOfPositiveElements(array);
    }

    public static void sumOfPositiveElements (int[][] array)
    {
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

}
