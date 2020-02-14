package com.example.algotask;

public class MaxProfit {
    // Say you have an array for which the ith element is the price of a given stock on day i.
    //
    //If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 1; i< prices.length; i++) {
            int v = prices[i];
            if (v > min) {
                maxProfit = Math.max(maxProfit, v - min);
            } else {
                min = Math.min(min, v);
            }
        }
        return maxProfit;
    }
}
