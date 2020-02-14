package com.example.algotask;

import java.util.*;

public class NumIslands {
    // Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
    // An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    // You may assume all four edges of the grid are all surrounded by water.
    //11000
    //11000
    //00100
    //00011      ===> 3

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int numIslands(char[][] grid) {
        Map<Integer, Set<Pair>> map = new HashMap<>();
        int num = 0;
        for (int x = 0; x< grid.length; x++) {
            for (int y = 0; y< grid[x].length; y++) {
                if (grid[x][y] == '1') {
                    Pair p = new Pair(x,y);
                    boolean found = false;
                    for (Set<Pair> set : map.values()) {
                        if (set.contains(p)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        num++;
                        map.putIfAbsent(num, new HashSet<>());
                        fillWithIsland(grid, x,y,num, map);
                    }
                }
            }

        }
        return map.keySet().size();
    }

    private void fillWithIsland(char[][] grid, int x, int y, int num, Map<Integer, Set<Pair>> map) {
        if (grid[x][y] == '1') {
            Set<Pair> set = map.get(num);
            Pair p = new Pair(x, y);
            if (set.contains(p)) return;
            set.add(p);
        }
        if (x+1 < grid.length) {
            if (grid[x+1][y] == '1') {
                fillWithIsland(grid, x + 1, y, num, map);
            }
        }
        if (x-1 >= 0) {
            if (grid[x-1][y] == '1') {
                fillWithIsland(grid, x - 1, y, num, map);
            }
        }
        if (y+1 < grid[x].length) {
            if (grid[x][y+1] == '1') {
                fillWithIsland(grid, x, y + 1, num, map);
            }
        }
        if (y-1 >=0) {
            if (grid[x][y-1] == '1') {
                fillWithIsland(grid, x, y - 1, num, map);
            }
        }
    }

    public static void main(String[] args) {
        NumIslands n = new NumIslands();
       // int i = n.numIslands(new char[][] {{'1','1','1'}, {'0','1','0'}, {'1','1','1'}});
        int i2 = n.numIslands(new char[][] {{'1','1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'},{'0','0','0','0','0'}});
        System.out.println(i2);
    }
}
