package com.example.algotask;

public class RollDiceWithSum {
    /*
    You have d dice, and each die has f faces numbered 1, 2, ..., f.

    Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.
     */
    long[][] cache = null;

    public int numRollsToTarget(int d, int f, int target) {
        cache = new long[d+1][target+1];
        for (int i = 0; i<=d; i++) {
            for (int j = 0; j<=target; j++) {
                cache[i][j] = -1;
            }
        }


        long cnt = 0;
        for (int i = 1; i<=f; i++) {
            if (d == 1) {
                if (i == target) {
                    cnt++;
                }
            } else {
                if (target - i > 0) {
                    if ((target - i) >= 1*(d -1) && (target -i) <= f * (d-1)) {
                        long comb = getComb(d - 1, f, target - i);
                        cnt += comb % 1000000007;
                    }
                }
            }
        }
        return (int) (cnt % 1000000007);
    }

    private long getComb(int d, int f, int target) {
        if (target <= 0) return 0;
        if (d == 1) {
            if (f >= target) {
                return 1;
            } else {
                return 0;
            }
        }
        if (cache[d][target] != -1) {
            return cache[d][target];
        }

        long cnt = 0;
        for (int i = 1; i<=Math.min(f, target - 1); i++) {
            if ((target - i) >= 1*(d -1) && (target -i) <= f * (d-1)) {
                long comb = getComb(d - 1, f, target - i);
                cnt += comb % 1000000007;
            }
        }
        cache[d][target] = cnt;
        return cnt;

    }


    public static void main(String[] args) {
        RollDiceWithSum rd = new RollDiceWithSum();
        System.out.println(rd.numRollsToTarget(20,6,50));
    }

}
