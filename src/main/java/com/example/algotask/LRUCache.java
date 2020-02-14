package com.example.algotask;

import java.util.*;

public class LRUCache {
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    private Map<Integer, Element> map = new HashMap<>();

    static class Element implements Comparable<Element> {
        int key;
        int value;
        long timestamp;

        public Element(int key, int value) {
            this.key = key;
            this.value = value;
            this.timestamp = System.nanoTime();
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(o.timestamp, this.timestamp);
        }
    }

    //private PriorityQueue<Element> pq = new PriorityQueue<>();

    final private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Element element = map.get(key);
        if (element == null) return -1;

        element.timestamp = System.nanoTime();
        return element.value;
    }

    public void put(int key, int value) {
        if (capacity == map.size()) {
            if (map.containsKey(key)) {
                map.get(key).value = value;
                map.get(key).timestamp = System.nanoTime();
            } else {
                removeLeastRecentlyUsed();
            }
        }
        Element element = new Element(key, value);
        map.put(key, element);
        //pq.add(element);
    }

    private void removeLeastRecentlyUsed() {

        Element minElement = null;
        //minElement = pq.poll();

        for (Element element : map.values()) {
            if (minElement == null || element.timestamp < minElement.timestamp) {
                minElement = element;
            }
        }
        if (minElement != null) {
            map.remove(minElement.key);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );
        System.out.println(cache.get(2));       // returns 1

        cache.put(2, 6);
        System.out.println(cache.get(1));       // returns 1
        cache.put(1, 5);    // evicts key 2
        cache.put(1, 2);    // evicts key 2
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(2));       // returns -1 (not found)
    }
}
