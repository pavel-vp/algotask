package com.example.algotask;

import java.util.HashSet;
import java.util.Set;

public class BinaryTreeSearchSum {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (set.contains(root.val)) return true;
        set.add(k - root.val);
        boolean inLeft = false;
        if (root.left != null) {
            inLeft = findTarget(root.left, k);
        }
        boolean inRight = false;
        if (root.right != null) {
            inRight = findTarget(root.right, k);
        }
        return inRight || inLeft;
    }
}
