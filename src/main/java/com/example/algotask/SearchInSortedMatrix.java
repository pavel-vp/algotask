package com.example.algotask;

import java.util.Arrays;

public class SearchInSortedMatrix {
    // Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
    //
    //Integers in each row are sorted in ascending from left to right.
    //Integers in each column are sorted in ascending from top to bottom.

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        for (int[] row : matrix) {
            if (row[0] <= target && target <= row[row.length-1]) {
                boolean found = findInRow(row, target);
                if (found) return true;
            } else {
                if (row[0] > target) return false;
            }
        }
        return false;
    }

    private boolean findInRow(int[] row, int target) {
        return Arrays.binarySearch(row, target) >= 0;
    }


}
