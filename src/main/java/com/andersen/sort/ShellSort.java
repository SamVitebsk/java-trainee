package com.andersen.sort;

public class ShellSort {
    public static int[] sort(int[] arr) {
        int gap = arr.length / 2;

        while (gap >= 1) {
            for (int right = 0; right < arr.length; right++) {
                for (int i = right - gap; i >= 0; i -= gap) {
                    if (arr[i] > arr[i + gap]) {
                        swap(arr, i, i + gap);
                    }
                }
            }

            gap /= 2;
        }

        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
