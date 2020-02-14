package com.example.algotask;

import java.util.HashMap;
import java.util.Map;

public class FindingLCAIntree {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, Integer> path1 = new HashMap<>();
        fillPath(root, p, path1, 0);

        Map<TreeNode, Integer> path2 = new HashMap<>();
        fillPath(root, q, path2, 0);
        TreeNode result = null;
        int maxLevel = Integer.MIN_VALUE;
        for (TreeNode n1 : path1.keySet()) {
            if (path2.containsKey(n1)) {
                int level = path1.get(n1);
                if (level > maxLevel) {
                    result = n1;
                    maxLevel = level;
                }
            }
        }
        return result;

    }

    private boolean fillPath(TreeNode root, TreeNode p, Map<TreeNode, Integer> path, int level) {
        if (root.val == p.val) {
            path.put(root, level);
            return true;
        }
        if (root.left != null) {
            if (fillPath(root.left, p, path, level + 1)) {
                path.put(root, level);
                return true;
            }
        }
        if (root.right != null) {
            if (fillPath(root.right, p, path, level + 1)) {
                path.put(root, level);
                return true;
            }
        }
        return false;
    }

}
