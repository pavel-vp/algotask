package com.example.algotask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatBinaryTree {
    // Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    //
    //Initially, all next pointers are set to NULL.
    // ++You may only use constant extra space.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        @Override
        public String toString() {
            return ""+val+"->"+(next==null?"#":next.val);
        }
    }

    public Node connect(Node root) {
        if (root == null || root.left == null && root.right == null) return root;
        Map<Integer, List<Node>> nodes = new HashMap<>();
        fillNodesMap(root, nodes, 0);
        fillNext(nodes);
        return root;
    }

    private void fillNext(Map<Integer, List<Node>> nodes) {
        for(List<Node> nodeList : nodes.values()) {
            Node next = null;
            for (Node node : nodeList) {
                node.next = next;
                next = node;
            }
        }
    }

    private void fillNodesMap(Node node, Map<Integer, List<Node>> nodes, int level) {
        nodes.putIfAbsent(level, new ArrayList<>());
        List<Node> list = nodes.get(level);
        list.add(node);

        if (node.right != null) {
            fillNodesMap(node.right, nodes, level + 1);
        }
        if (node.left != null) {
            fillNodesMap(node.left, nodes, level + 1);
        }
    }

    public static void main(String[] args) {
        FlatBinaryTree b = new FlatBinaryTree();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n7 = new Node(7);
        n1.right = n3;
        n1.left = n2;
        n2.right = n5;
        n2.left = n4;
        n3.right = n7;
        b.connect(n1);



    }

}
