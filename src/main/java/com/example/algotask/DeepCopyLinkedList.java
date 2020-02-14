package com.example.algotask;

import java.util.HashMap;
import java.util.Map;

public class DeepCopyLinkedList {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Integer> mapNodePosition = new HashMap<>();
        Map<Integer, Node> mapNumPositionNew = new HashMap<>();
        Integer[] randomPointers = new Integer[10000];
        Node originalHead = head;
        Node newHead = null;
        // First copy the list without random
        Node node = head;
        Node prevNode = null;
        Node nodeNew = null;
        int pos = 0;
        while (node != null) {
            nodeNew = new Node(node.val);
            if (prevNode != null) {
                prevNode.next = nodeNew;
            }
            if (newHead == null) {
                newHead = nodeNew;
            }
            mapNodePosition.put(node, pos);
            mapNumPositionNew.put(pos, nodeNew);
            node = node.next;
            prevNode = nodeNew;
            pos++;
        }

        // fill pos of random

        node = head;
        pos = 0;
        while (node != null) {
            if (node.random != null) {
                int posRandom = mapNodePosition.get(node.random);
                randomPointers[pos] = posRandom;
            }
            node = node.next;
            pos++;
        }

        // Walk throuog new, setting random
        nodeNew = newHead;
        pos = 0;
        while (nodeNew != null) {
            if (randomPointers[pos] != null) {
                nodeNew.random = mapNumPositionNew.get(randomPointers[pos]);
            }
            nodeNew = nodeNew.next;
            pos++;
        }

        return newHead;
    }

}
