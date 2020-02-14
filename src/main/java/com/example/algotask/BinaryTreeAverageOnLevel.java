package com.example.algotask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeAverageOnLevel {

    // Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        fillMap(root, map, 0);

        List<Double> result = new ArrayList<>();
        map.keySet().stream()
                .forEach(integer -> result.add(0d));

        for (Integer level : map.keySet() ) {
            Double avg = map.get(level).stream()
                    .mapToInt(value -> value)
                    .average()
                    .getAsDouble();
            result.set(level, avg);
        }

        return result;
    }

    private void fillMap(TreeNode root, Map<Integer, List<Integer>> map, int level) {
        map.putIfAbsent(level, new ArrayList<>());
        map.get(level).add(root.val);
        if (root.left != null) {
            fillMap(root.left, map, level + 1);
        }
        if (root.right != null) {
            fillMap(root.right, map, level + 1);
        }
    }


}
