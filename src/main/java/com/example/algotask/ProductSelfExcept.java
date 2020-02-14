package com.example.algotask;

public class ProductSelfExcept {
    /*
    Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
    Note: Please solve it without division and in O(n).
    Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
     */
    public int[] productExceptSelf_bad(int[] nums) {
        int all = 1;
        for (int i = 0; i< nums.length; i++) {
            all = all * nums[i];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i< nums.length; i++) {
            res[i] = all / nums[i];
        }
        return res;
    }

    public int[] productExceptSelf_N(int[] nums) {
        int[] res = new int[nums.length];
        int left = 1;
        for (int i = nums.length - 1; i>=0; i--) {
            res[i] = left;
            left = left * nums[i];
        }

        int right = 1;
        for (int i = 0; i< nums.length; i++) {
            int left2 = res[i];
            res[i] = left2 * right;
            right = right * nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        ProductSelfExcept ps = new ProductSelfExcept();
        System.out.println(ps.productExceptSelf_N(new int[] {1,2,3,4}));

    }
}
