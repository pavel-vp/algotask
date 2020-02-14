package com.example.algotask;

public class DeleteInBST {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    static class FoundNode {
        TreeNode parent;
        TreeNode node;

        public FoundNode(TreeNode parent, TreeNode node) {
            this.parent = parent;
            this.node = node;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        FoundNode nodeToDelete = findNode(null, root, key);
        if (nodeToDelete != null) {
            root = deleteNodeInBSTAndReturnNewRoot(root, nodeToDelete);
        }
        return root;
    }

    private TreeNode deleteNodeInBSTAndReturnNewRoot(TreeNode root, FoundNode nodeToDelete) {
        // deleting node without children
        if (nodeToDelete.node.left == null && nodeToDelete.node.right == null) {
            if (nodeToDelete.parent == null) return null; // one node and it's root
            // node without children
            if (nodeToDelete.parent.left == nodeToDelete.node) {
                nodeToDelete.parent.left = null;
            } else {
                nodeToDelete.parent.right = null;
            }
            return root; // tree unchanged
        }
        // deleting node with 1 children
        if (nodeToDelete.node.left == null || nodeToDelete.node.right == null) {
                if (nodeToDelete.node.left != null) {
                    if (nodeToDelete.parent == null) { // deleting root with 1 children
                            root = nodeToDelete.node.left;
                    } else {
                        if (nodeToDelete.parent.left == nodeToDelete.node) {
                            nodeToDelete.parent.left = nodeToDelete.node.left;
                        } else {
                            nodeToDelete.parent.right = nodeToDelete.node.left;
                        }
                    }
                } else {
                    if (nodeToDelete.parent == null) { // deleting root with 1 children
                            root = nodeToDelete.node.right;
                    } else {
                        if (nodeToDelete.parent.left == nodeToDelete.node) {
                            nodeToDelete.parent.left = nodeToDelete.node.right;
                        } else {
                            nodeToDelete.parent.right = nodeToDelete.node.right;
                        }
                    }
                }
            return root;
        }

        if (nodeToDelete.parent == null) { // deleting root with children
            if (nodeToDelete.node.left == null) { // only right
                return nodeToDelete.node.right;
            }
            if (nodeToDelete.node.right == null) { // only left
                return nodeToDelete.node.left;
            }
        }

        FoundNode minRightNode = findLeftMostNode(nodeToDelete.node, nodeToDelete.node.right);
        if (minRightNode.parent.left == minRightNode.node) {
            minRightNode.parent.left = minRightNode.node.right;
        } else {
            minRightNode.parent.right = minRightNode.node.right;
        }

        if (nodeToDelete.parent == null) {
            root = minRightNode.node;
        } else {
            if (nodeToDelete.parent.left == nodeToDelete.node) {
                nodeToDelete.parent.left = minRightNode.node;
            } else {
                nodeToDelete.parent.right = minRightNode.node;
            }
        }
        minRightNode.node.left = nodeToDelete.node.left;
        minRightNode.node.right = nodeToDelete.node.right;
        return root;
    }

    private FoundNode findLeftMostNode(TreeNode parent, TreeNode node) {
        if (node.left == null) {
            return new FoundNode(parent, node);
        }
        return findLeftMostNode(node, node.left);
    }

    private FoundNode findNode(TreeNode root, TreeNode node, int key) {
        if (node.val == key) return new FoundNode(root, node);
        if (key < node.val && node.left != null) return findNode(node, node.left, key);
        if (node.val < key && node.right != null) return findNode(node, node.right, key);
        return null;
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.right = n2;

        DeleteInBST d = new DeleteInBST();
        TreeNode root = d.deleteNode(n1, 2);
        System.out.println(root);
    }

}
