package com.example.algotask;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoArraysPermutate {

    static String twoArrays(int k, int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length || A.length < 1) return "NO";
        Arrays.sort(A);
        Arrays.sort(B);
        Set<Integer> bUsed = new HashSet<>();
        for (int a : A) {
            boolean found = false;
            for (int i = 0; i<B.length; i++) {
                int b = B[i];
                if ((a + b) >= k && !bUsed.contains(i)) {
                    bUsed.add(i);
                    found = true;
                    break;
                }
            }
            if (!found) return "NO";
        }
        return "YES";
    }

}
