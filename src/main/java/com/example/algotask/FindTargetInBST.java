package com.example.algotask;

import java.util.HashSet;
import java.util.Set;

public class FindTargetInBST {
    // Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        int diff = k - root.val;
        if (set.contains(diff)) return true;
        set.add(root.val);
        boolean isFound = false;
        if (root.left != null) {
            isFound = isFound || findTarget(root.left, k);
        }
        if (root.right != null) {
            isFound = isFound || findTarget(root.right, k);
        }
        return isFound;
    }

}
