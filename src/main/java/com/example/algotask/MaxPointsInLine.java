package com.example.algotask;

import java.util.*;

public class MaxPointsInLine {
    // Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) return 0;
        int cntXX = getMaxXX(points);
        int cntYY = getMaxYY(points);
        int cntXY = getMaxXY(points);

        return Math.max(cntXX, Math.max(cntYY, cntXY));

    }

    private int getMaxXX(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int[] point : points) {
            map.compute( point[0], (k, v) -> v == null ? 1 : v + 1);
            count = Math.max(count, map.get(point[0]));
        }
        return count;
    }
    private int getMaxYY(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int[] point : points) {
            map.compute( point[1], (k, v) -> v == null ? 1 : v + 1);
            count = Math.max(count, map.get(point[1]));
        }
        return count;
    }

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

    private int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }

    private int getMaxXY(int[][] points) {
        Map<String, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            int[] point1 = points[i];
            int same = 0;
            int count = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (i != j) {
                    int[] point2 = points[j];
                    int dx = point2[0] - point1[0];
                    int dy = point2[1] - point1[1];

                    if (dx == 0 && dy == 0) {
                        same++;
                    } else {
                        if (dx == 0) {
                            dy = 1;
                        } else {
                            if (dy == 0) {
                                dx = 1;
                            } else {
                                int d = getGCD(dx, dy);
                                dx = dx / d;
                                dy = dy / d;
                            }
                        }
                        map.putIfAbsent(dx +","+ dy, 0);
                        map.put(dx +","+ dy, map.get(dx +","+ dy) + 1);
                        count = Math.max(count, map.get(dx +","+ dy));
                    }
                }
            }
            result = Math.max(result, count + same + 1);
            map.clear();
        }
        return result;
    }

    public static void main(String[] args) {
        MaxPointsInLine m = new MaxPointsInLine();
        int mp = m.maxPoints(new int[][] {{0,0}, {0,0}});
        System.out.println(mp);
//        System.out.println(m.findDivisor(-6));
//        System.out.println(m.findDivisor(0));
    }
}
