package com.andersen.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InsertingSortTest {
    int[] arr = {5, 11, 9, 7, 6, 10};
    int[] expected = {5, 6, 7, 9, 10, 11};

    @Test
    void sort() {
        InsertingSort.sort(arr);

        assertArrayEquals(expected, arr);
    }
}