package com.example.algotask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CombinationCount {
    // A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
    //
    //The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
    //
    //How many possible unique paths are there?
    static class Pair {
        Integer x;
        Integer y;

        public Pair(int m, int n) {
            this.x = m;
            this.y = n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(x, pair.x) &&
                    Objects.equals(y, pair.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    Map<Pair, Integer> map = new HashMap<>();
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        Pair p = new Pair(m,n);
        if (map.containsKey(p)) return map.get(p);
        int count =0;
        for (int i = 0; i <m; i++) {
            count = count + uniquePaths(m - i, n - 1);
        }
        map.put(p, count);
        return count;
    }


    public static void main(String[] args) {
        CombinationCount cc = new CombinationCount();
        System.out.println(cc.uniquePaths(7,3));
    }
}
