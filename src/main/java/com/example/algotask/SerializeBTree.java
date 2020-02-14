package com.example.algotask;

import java.util.*;

public class SerializeBTree {
//You may serialize the following tree:
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//       /
//      6
//as "[1, 2,3, null,null,4,5, null,null,null,null,null,null,6,null]"

    // FIXME: too much memory consumption
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
   }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null,";
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val + "," + left + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;

        String[] s = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(s));
        return deserialize(queue);
    }

    private TreeNode deserialize(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("null")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.left = deserialize(queue);
        node.right = deserialize(queue);
        return node;
    }


    public static void main(String[] args) {
        SerializeBTree s = new SerializeBTree();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        t4.left = t6;
        String sr = s.serialize(t1);
        System.out.println(sr);
        System.out.println(s.serialize(null));

        TreeNode t = s.deserialize(sr);
        System.out.println(t);
    }

}
