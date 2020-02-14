package com.example.algotask;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BiValuedArray {

    private int getMaxOccurance(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            map.compute(value, (k, v) -> v == null ? 1 : v + 1);
        }
        return map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }


    /*
    FIXME: WRONG SOLUTION

     Find longest sequence length of two digits in an array
     [1,2,3,2] = 3
     [1,1] = 2
     [4,4,4,5,4,1,1,1] = 5
     */

    public int solution(int[] A) {
        if (A == null) return 0;
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< A.length; i++) {
            int v = A[i];
            if (!map.containsKey(v)) {
                if (map.size()==2) {
                    // calc max previously
                    int max = calcMaxDiff(map);
                    result = Math.max(result, max + 1);
                    removeFirst(map);
                }
                map.put(v, i);
            } else {
                if (map.size()==2) {
                    map.put(v, i);
                    int start = calcStart(map);
                    result = Math.max(result, i - start + 1);
                }
            }
        }
        if (map.size() == 1) {
            return A.length;
        }
        if (result == 0 && map.size() > 0) {
            return calcMaxDiff(map) + 1;
        }
        return result;
    }

    private int calcStart(Map<Integer, Integer> map) {
        Integer[] vals = new Integer[2];
        int i = 0;
        for (Integer key : map.keySet()) {
            vals[i]=map.get(key);
            i++;
        }
        return Math.min(vals[0], vals[1]);
    }


    private void removeFirst(Map<Integer, Integer> map) {
        Integer[] vals = new Integer[2];
        int i = 0;
        for (Integer key : map.keySet()) {
            vals[i]=map.get(key);
            i++;
        }
        int minVal = Math.min(vals[0], vals[1]);
        Integer keyToRemove = null;
        for (Integer key : map.keySet()) {
            if (map.get(key) == minVal) {
                keyToRemove = key;
            }
        }
        map.remove(keyToRemove);
    }

    private int calcMaxDiff(Map<Integer, Integer> map) {
        Integer[] vals = new Integer[2];
        int i = 0;
        for (Integer key : map.keySet()) {
            vals[i]=map.get(key);
            i++;
        }
        return Math.abs(vals[1] - vals[0]);
    }

    public static void main(String[] args) {
        BiValuedArray bv = new BiValuedArray();
//        System.out.println(bv.solution(new int[] {4,4,5,4,4,1,1,1}));
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(bv.getMaxOccurance(new int[] {1,1,2,2,2,2,3}));

    }

}
