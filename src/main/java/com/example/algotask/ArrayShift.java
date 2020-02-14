package com.example.algotask;

public class ArrayShift {
    public int[] solution(int[] A, int K) {
        int shift = K % A.length;
        if (shift == 0) { return A; }
        int[] res = new int[A.length];
        for (int i = 0; i< A.length; i++) {
            res[i] = A[shift];
            shift++;
            if (shift == A.length)
                shift = 0;
        }
        return res;
    }

    public static void main(String[] args) {

        ArrayShift a = new ArrayShift();
        System.out.println(a.solution(new int[] {3, 8, 9, 7, 6}, 3));
    }

}
