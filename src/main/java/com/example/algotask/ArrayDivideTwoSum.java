package com.example.algotask;

import java.util.*;

public class ArrayDivideTwoSum {
    // Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
    //
    //Note:
    //
    //Each of the array element will not exceed 100.
    //The array size will not exceed 200.

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        int sumAll = Arrays.stream(nums)
                .sum();

        if (sumAll % 2 == 1) return false;
        int sumTarget = sumAll / 2;
        //Set<Integer> idxs = new TreeSet<>();
        //return tryFind(idxs, 0, 0, sumTarget, nums);

        boolean[][] dp = new boolean[sumTarget+1][nums.length+1];
        dp[0][0] = true;
        for (int i = 0; i <= nums.length; i++) {
            dp[0][1] = true;
        }
        for (int sum = 1; sum <= sumTarget; sum++) {
            for (int j = 1; j <= nums.length; j++) {

                if (sum >= nums[j-1]) {
                    dp[sum][j] = dp[sum][j-1] || dp[sum - nums[j-1]][j-1];
                } else {
                    dp[sum][j] = dp[sum][j-1];
                }
            }
        }

        return dp[sumTarget][nums.length];
    }

    // why this isn't working???
    private boolean tryFind(Set<Integer> set, int i, int sumSet, int sumTarget, int[] nums) {
        if (sumSet == sumTarget) return true;
        if (i>=nums.length) return false;
            if (!set.contains(i)) {

                if (sumSet + nums[i] == sumTarget) {
                    set.add(i);
                    return true;
                }
                if (sumSet + nums[i] < sumTarget) {
                    // try add
                    set.add(i);
                    if (tryFind(set, i + 1, sumSet + nums[i], sumTarget, nums)) {
                        return true;
                    }
                    // not found
                    set.remove(i);
                } else {
                    if (tryFind(set, i + 1, sumSet, sumTarget, nums)) {
                        return true;
                    }
                }
            }
        return false;
    }

    public static void main(String[] args) {
        ArrayDivideTwoSum d = new ArrayDivideTwoSum();
        System.out.println(d.canPartition(new int[] {1,5,11,5}));
//        System.out.println(d.canPartition(new int[] {1,2,3,5}));
//        System.out.println(d.canPartition(new int[] {1,3,4,4}));
        System.out.println(d.canPartition(new int[] {1,1,1,3,1,1}));
        System.out.println(d.canPartition(new int[] {1,2,3,4,5,6,7}));
//        System.out.println(d.canPartition(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100}));
    }

}
