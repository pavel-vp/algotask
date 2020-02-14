package com.example.algotask;

import java.util.HashMap;
import java.util.Map;

public class DistributeCandies {
    /*
    Given an integer array with even length, where different numbers in this array represent different kinds of candies.
    Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister.
    Return the maximum number of kinds of candies the sister could gain.
     */
    public int distributeCandies(int[] candies) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int type : candies) {
            Integer cnt = map.get(type);
            if (cnt == null) {
                cnt = 0;
            }
            map.put(type, cnt + 1);
        }
        int result = Math.min(candies.length / 2, map.size());
        return result;
    }

    public static void main(String[] args) {
        DistributeCandies ds = new DistributeCandies();
        System.out.println(ds.distributeCandies(new int[] {2,2,2,2}));
    }
}
