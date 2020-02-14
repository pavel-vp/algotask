package com.example.algotask;

public class MaxAbsValueOf2Arr {
    // Given two arrays of integers with equal lengths, return the maximum value of:
    //
    //|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
    //
    //where the maximum is taken over all 0 <= i, j < arr1.length.

    // FIXME: timing too long n^2


    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i<arr1.length; i++) {
            for (int j = 0; j<arr2.length; j++) {
                // |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
                int val = Math.abs(arr1[i] - arr1[j]) + Math.abs(arr2[i] - arr2[j]) + Math.abs(i - j);
                max = Math.max(max, val);
            }
        }

        return max;
    }

}
