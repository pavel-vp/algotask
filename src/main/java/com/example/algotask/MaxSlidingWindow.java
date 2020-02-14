package com.example.algotask;

import java.util.Arrays;

public class MaxSlidingWindow {

    // FIXME: wrong solution. COuld do it only in n^2 time
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        if (k == 1) {
            return nums;
        }
        int[] res = new int[nums.length - k + 1];


        int max = Integer.MIN_VALUE;
        int maxS = Integer.MIN_VALUE;

        for (int i = 0; i< k; i++) {
            max = Math.max(max, nums[i]);
        }
        boolean isFound = false;
        for (int i = 0; i< k; i++) {
            if (max != nums[i]) {
                maxS = Math.max(maxS, nums[i]);
                isFound = true;
            }
        }
        if (!isFound) {
            maxS = max;
        }
        res[0] = max;
        for (int i = k; i< nums.length ; i++) {
            int prevValue = nums[i-k];
            int value = nums[i];

            if (prevValue == max) {
                if (value >= maxS) {
                    max = value;
                } else {
                    max = maxS;
                    maxS = getMaxS(nums, i - k + 1, i, max);
                }
            } else {
                if (value >= max) {
                    maxS = max;
                    max = value;
                } else {
                    if (prevValue == maxS) {
                        maxS = getMaxS(nums, i - k + 1, i, max);
                    } else {
                        maxS = Math.max(maxS, value);
                    }
                }
            }

            res[i-k + 1] = max;
        }
        return res;
    }

    private int getMaxS(int[] nums, int b, int e, int max) {
        int maxS = Integer.MIN_VALUE;
        boolean isFound = false;
        for (int i = b; i<= e; i++) {
            if (max != nums[i]) {
                maxS = Math.max(maxS, nums[i]);
                isFound = true;
            }
        }
        if (!isFound) {
            maxS = max;
        }
        return maxS;
    }
    public static void main(String[] args) {
        MaxSlidingWindow m = new MaxSlidingWindow();
        int[] max = m.maxSlidingWindow(new int[] {5183,2,2,71,30,6,8,78,58,31}, 10);
        System.out.println(Arrays.toString(max));
    }

}
