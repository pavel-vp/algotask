package com.example.algotask;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeZigzagLevelTraversal {
    // Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

    static class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        fillLevelInfo(root, true, list, 1);
        return list;
    }

    private void fillLevelInfo(TreeNode root, boolean toRight, List<List<Integer>> list, int level) {
        List<Integer> linkedList;
        if (level > list.size()) {
            linkedList = new LinkedList<>();
            list.add(linkedList);
        } else {
            linkedList = list.get(level-1);
        }
        if (toRight) {
            ((LinkedList) linkedList).addLast(root.val);
            if (root.left != null) {
                fillLevelInfo(root.left, !toRight, list, level + 1);
            }
            if (root.right != null) {
                fillLevelInfo(root.right, !toRight, list, level + 1);
            }
        } else {
            ((LinkedList) linkedList).addFirst(root.val);
            if (root.left != null) {
                fillLevelInfo(root.left, !toRight, list, level + 1);
            }
            if (root.right != null) {
                fillLevelInfo(root.right, !toRight, list, level + 1);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n7 = new TreeNode(7);
        TreeNode n15 = new TreeNode(15);

        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        TreeZigzagLevelTraversal n = new TreeZigzagLevelTraversal();
        System.out.println(n.zigzagLevelOrder(n3));
    }


}
