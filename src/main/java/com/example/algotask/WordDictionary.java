package com.example.algotask;

import java.util.ArrayList;
import java.util.List;

public class WordDictionary {

    static class Node {
        char ch;
        boolean canFinish = false;
        List<Node> children = new ArrayList<>();

        public Node(char ch) {
            this.ch = ch;
        }
    }
    Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node('.');
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        addToTrie(root, word);
    }

    private void addToTrie(Node node, String word) {
        char chNew = word.charAt(0);
        Node foundNode = null;
        for (Node n : node.children) {
            if (n.ch == chNew) {
                foundNode = n;
                break;
            }
        }
        if (foundNode == null) {
            foundNode = new Node(chNew);
            node.children.add(foundNode);
        }
        if (word.length() == 1) {
            foundNode.canFinish = true;
        } else {
            addToTrie(foundNode, word.substring(1));
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        for (Node node : root.children) {
            if (findInTrie(node, word)) return true;
        }
        return false;
    }

    private boolean findInTrie(Node node, String word) {
        char ch = word.charAt(0);
        if (ch != '.' && ch != node.ch) {
            return false;
        }
        if (word.length() == 1) return node.canFinish;
        for (Node child : node.children) {
            if (findInTrie(child, word.substring(1))) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary w = new WordDictionary();
        w.addWord("at");
        w.addWord("and");
        w.addWord("an");
        System.out.println(w.search("a"));
    }

}
