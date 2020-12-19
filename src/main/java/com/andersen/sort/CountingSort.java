package com.andersen.sort;

public class CountingSort {
    public static int[] sort(int[] arr, int maxValue) {
        int[] numsCounts = new int[maxValue + 1];

        for (int num : arr) {
            numsCounts[num]++;
        }

        int[] sortedArr = new int[arr.length];
        int currentSortedIndex = 0;

        for (int i = 0; i < numsCounts.length; i++) {
            int count = numsCounts[i];

            for (int j = 0; j < count; j++) {
                sortedArr[currentSortedIndex] = i;
                currentSortedIndex++;
            }
        }

        return sortedArr;
    }
}
