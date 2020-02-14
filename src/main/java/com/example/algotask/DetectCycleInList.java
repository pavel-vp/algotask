package com.example.algotask;

public class DetectCycleInList {
    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode node = head;
        ListNode node2 = head.next;
        while (node != null && node2 != null && node != node2) {
            node = node.next;
            if (node2.next == null) return false;
            node2 = node2.next.next;
        }
        return node == node2;
    }
}
