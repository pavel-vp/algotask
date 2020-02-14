package com.example.algotask;

import java.util.HashMap;
import java.util.Map;

public class CPUIntervals {
    // Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
    //
    // However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
    //
    // You need to return the least number of intervals the CPU will take to finish all the given tasks.

    public int leastInterval(char[] tasks, int n) {
        if (tasks == null) return 0;
        if (n == 0) return tasks.length;

        int result = 0;

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (char ch : tasks) {
            map.compute(ch, (character, integer) -> map.get(character) == null ? 1 : map.get(character) + 1);
            max = Math.max(max, map.get(ch));
        }

        int time_slots = (max - 1) * (n + 1);
        for (Character ch : map.keySet()) {
            int cnt = map.get(ch);
            time_slots = time_slots - Math.min(max-1, cnt);
        }

        return time_slots > 0 ? time_slots + tasks.length : tasks.length;
    }

    public static void main(String[] args) {
        CPUIntervals c = new CPUIntervals();
        System.out.println(c.leastInterval(new char[] {'A','A','A','B','B', 'B'}, 2));
//        System.out.println(c.leastInterval(new char[] {'A','B','C','D','E','A','B','C','D','E'}, 4));
    }
}
