package com.andersen.sort;

public class QuickSort {
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);

        return arr;
    }

    public static void quickSort(int[] arr, int leftBorder, int rightBorder) {
        int left = leftBorder;
        int right = rightBorder;
        int pivot = arr[(left + right) / 2];

        do {
            while (arr[left] < pivot) {
                left++;
            }

            while (arr[right] > pivot) {
                right--;
            }

            if (left <= right) {
                if (left < right) {
                    swap(arr, left, right);
                }

                left++;
                right--;
            }
        } while (left <= right);

        if (left < rightBorder) {
            quickSort(arr, left, rightBorder);
        }

        if (leftBorder < right) {
            quickSort(arr, leftBorder, right);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
