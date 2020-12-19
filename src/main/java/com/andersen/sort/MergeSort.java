package com.andersen.sort;

public class MergeSort {
    public static int[] sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void mergeSort(int[] arr, int left, int right) {
        int delimiter = left + ((right - left) / 2) + 1;

        if (delimiter > 0 && right > (left + 1)) {
            mergeSort(arr, left, delimiter - 1);
            mergeSort(arr, delimiter, right);
        }

        int[] buffer = new int[right - left + 1];
        int cursor = left;

        for (int i = 0; i < buffer.length; i++) {
            if (delimiter > right || arr[cursor] < arr[delimiter]) {
                buffer[i] = arr[cursor];
                cursor++;
            } else {
                buffer[i] = arr[delimiter];
                delimiter++;
            }
        }

        System.arraycopy(buffer, 0, arr, left, buffer.length);
    }
}
