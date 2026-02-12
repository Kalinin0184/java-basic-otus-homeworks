package ru.otus.java.basic.homeworks;

import java.util.Arrays;

public class ArrayUtils {

    public static int[] elementsAfterLastOne(int[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input array must not be null");
        }

        int lastOneIndex = -1;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 1) {
                lastOneIndex = i;
            }
        }

        if (lastOneIndex == -1) {
            throw new RuntimeException("Array does not contain 1");
        }

        if (lastOneIndex == input.length - 1) {
            return new int[0];
        }

        return Arrays.copyOfRange(input, lastOneIndex + 1, input.length);
    }

    public static boolean containsOnlyOnesAndTwos(int[] input) {
        if (input == null || input.length == 0) {
            return false;
        }

        boolean hasOne = false;
        boolean hasTwo = false;

        for (int value : input) {
            if (value == 1) {
                hasOne = true;
            } else if (value == 2) {
                hasTwo = true;
            } else {
                return false;
            }
        }

        return hasOne && hasTwo;
    }
}