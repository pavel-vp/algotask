package com.example.algotask;

import java.util.ArrayList;
import java.util.List;

public class WaterTrapped {
    // Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
    // Example:
    //
    //Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    //Output: 6

    // FIXME: too long execution, bad algo

    static class Border {
        int prevMax;
        int max;
        int filledBy;
        List<Integer> between;

        public Border(int prevMax, int max, List<Integer> between) {
            this.prevMax = prevMax;
            this.max = max;
            this.between = between;
        }
    }

    List<Border> borderList;
    public int trap(int[] height) {
        borderList = new ArrayList<>();
        if (height == null || height.length <= 2) return 0;

        int result = 0;
        boolean wasGrow = false;
        List<Integer> between = new ArrayList<>();
        int max = Math.max(height[0], height[1]);
        int prevMax = Integer.MIN_VALUE;

        if (height[0] >= height[1]) {
            prevMax = height[0];
        }

        if (height[0] > height[1]) {
            between.add(height[1]);
            max = height[1];
        } else {
            wasGrow = true;
        }
        int prev = height[1];
        for (int i = 2; i<height.length; i++) {
            int curr = height[i];
            // going down
            if (curr < prev) {
                if (wasGrow) {
                    // try to calc prev result
                    result += calcResult(between, prevMax, max);
                    between = new ArrayList<>();
                    prevMax =  max;
                } else {
                    prevMax =  Math.max(max, prevMax);
                }
                max = curr;
                wasGrow = false;
            } else {
                // going up
                max = Math.max(max, curr);
                if (curr > prev) {
                    wasGrow = true;
                }
            }
            between.add(curr);
            prev = curr;
        }
        if (wasGrow) {
            result += calcResult(between, prevMax, max);
        }

        return result;


    }

    private int calcResult(List<Integer> between, int prevMax, int max) {
        int result = 0;
            int minPeakLocal = Math.min(max, prevMax);
            for (Integer d : between) {
                if (minPeakLocal > d) {
                    result += minPeakLocal - d;
                }
            }
        // through previous betweens
        // first, find elem with max peak (maxPrev)
        int maxPeakIdx = -1;
        for (int i = borderList.size() - 1; i>=0; i--) {
            Border border = borderList.get(i);
            if (maxPeakIdx == -1) {
                maxPeakIdx = i;
            } else {
                int maxPeak = border.prevMax;
                int maxPeakPrev =  borderList.get(maxPeakIdx).prevMax;
                if (maxPeak > maxPeakPrev) {
                    maxPeakIdx = i;
                }
            }
        }
        borderList.add(new Border(prevMax, max, between));
        if (maxPeakIdx != -1) {
            int maxPeakPrev = borderList.get(maxPeakIdx).prevMax;
            int minPeakGlobal = Math.min(max, maxPeakPrev);
            boolean lastWall = true;
            for (int i = borderList.size() - 1; i >= maxPeakIdx; i--) {
                Border border = borderList.get(i);
                int minPeak = Math.min(border.max, border.prevMax);
                if (minPeakGlobal > minPeak) {
                    for (int j = border.between.size() -1; j>=0; j--) {
                        Integer d = border.between.get(j);
                        if (!lastWall && d<minPeakGlobal) {
                            int lowerBound = Math.max(Math.max(minPeak,d), border.filledBy);
                            if (minPeakGlobal > lowerBound) {
                                result += minPeakGlobal - lowerBound;
                            }
                        }
                        lastWall = false;
                    }
                }
                lastWall = false;
                border.filledBy = Math.max(minPeakGlobal,border.filledBy);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        WaterTrapped w = new WaterTrapped();
//        System.out.println(w.trap(new int[] {1,4,0,3,1,2,1,4,1}));
//        System.out.println(w.trap(new int[] {1,0,2,1,0,3}));
//        System.out.println(w.trap(new int[] {1,0,2}));
//        System.out.println(w.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
//        System.out.println(w.trap(new int[] {1,2,3,2,1}));
//        System.out.println(w.trap(new int[] {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3}));
        System.out.println(w.trap(new int[] {1,9,7,1,3,6,4,7,4,8,3,6,3,5,3,7}));
//      System.out.println(w.trap(new int[] {1,9,7,1,3,6,4,7,4,8,3,6,3,5,3,7}));
    }
}
