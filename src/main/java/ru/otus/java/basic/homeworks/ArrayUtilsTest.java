package ru.otus.java.basic.homeworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {

    @Test
    void testElementsAfterLastOne_example1() {
        int[] input = {1, 2, 1, 2, 2};
        int[] expected = {2, 2};

        int[] actual = ArrayUtils.elementsAfterLastOne(input);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testElementsAfterLastOne_oneAtEnd_returnsEmptyArray() {
        int[] input = {2, 2, 1};
        int[] expected = {};

        int[] actual = ArrayUtils.elementsAfterLastOne(input);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testElementsAfterLastOne_onlyOnes_returnsEmptyArray() {
        int[] input = {1, 1, 1};
        int[] expected = {};

        int[] actual = ArrayUtils.elementsAfterLastOne(input);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testElementsAfterLastOne_noOne_throwsException() {
        int[] input = {2, 2, 2, 2};

        assertThrows(RuntimeException.class,
                () -> ArrayUtils.elementsAfterLastOne(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_exampleTrue1() {
        int[] input = {1, 2};
        assertTrue(ArrayUtils.containsOnlyOnesAndTwos(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_exampleFalse1() {
        int[] input = {1, 1};
        assertFalse(ArrayUtils.containsOnlyOnesAndTwos(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_exampleFalse2() {
        int[] input = {1, 3};
        assertFalse(ArrayUtils.containsOnlyOnesAndTwos(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_exampleTrue2() {
        int[] input = {1, 2, 2, 1};
        assertTrue(ArrayUtils.containsOnlyOnesAndTwos(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_noTwos_false() {
        int[] input = {1, 1, 1};
        assertFalse(ArrayUtils.containsOnlyOnesAndTwos(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_noOnes_false() {
        int[] input = {2, 2, 2};
        assertFalse(ArrayUtils.containsOnlyOnesAndTwos(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_empty_false() {
        int[] input = {};
        assertFalse(ArrayUtils.containsOnlyOnesAndTwos(input));
    }

    @Test
    void testContainsOnlyOnesAndTwos_null_false() {
        assertFalse(ArrayUtils.containsOnlyOnesAndTwos(null));
    }
}
