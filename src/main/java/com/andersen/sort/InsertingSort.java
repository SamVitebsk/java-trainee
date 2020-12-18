package com.andersen.sort;

public class InsertingSort {
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int element = arr[i];
            int j = i;

            while (j > 0 && arr[j - 1] > element) {
                arr[j] = arr[j-1];
                j--;
            }

            arr[j] = element;
        }

        return arr;
    }
}
