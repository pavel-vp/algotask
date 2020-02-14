package com.example.algotask;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RelativeSortArray {
    /*
    Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

    Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            Integer cnt = map2.get(arr2[i]);
            if (cnt == null) {
                cnt = 0;
            }
            map2.put(arr2[i], cnt+1);
        }

        TreeMap<Integer, Integer> treeMap1 = new TreeMap<>();

        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            Integer cnt = map1.get(arr1[i]);
            if (cnt == null) {
                cnt = 0;
            }
            map1.put(arr1[i], cnt+1);
            if (!map2.containsKey(arr1[i])) {
                Integer cnt2 = treeMap1.get(arr1[i]);
                if (cnt2 == null) {
                    cnt2 = 0;
                }
                treeMap1.put(arr1[i], cnt2+1);
            }
        }

        int[] result = new int[arr1.length];
        int iRes = 0;
        for (int i = 0; i < arr2.length; i++) {
            Integer cnt1 = map1.get(arr2[i]);
            for (int j = 0; j< cnt1; j++) {
                result[iRes] = arr2[i];
                iRes++;
            }
        }
        for (Integer key : treeMap1.keySet()) {
            Integer cnt1 = treeMap1.get(key);
            for (int j = 0; j< cnt1; j++) {
                result[iRes] = key;
                iRes++;
            }
        }

        return result;

    }
}
