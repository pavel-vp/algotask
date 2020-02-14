package com.example.algotask;

import java.util.HashMap;
import java.util.Map;

public class Fibbonachi {
    Map<Integer, Integer> map = new HashMap<>();

    public int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        Integer n1 = map.get(N - 1);
        Integer n2 = map.get(N - 2);
        if (n1 == null) {
            n1 = fib(N - 1);
            map.put(N - 1, n1);
        }
        if (n2 == null) {
            n2 = fib(N - 2);
            map.put(N - 2, n2);
        }
        return n1 + n2;
    }

    public static void main(String[] args) {
        Fibbonachi fib = new Fibbonachi();
        System.out.println(fib.fib(4));
    }

}
