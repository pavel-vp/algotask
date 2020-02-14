package com.example.algotask;

public class ReverseBits {
    // Reverse bits of a given 32 bits unsigned integer.
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;

        for (int i = 0;;i++) {
            int bit = n & 1;
            res = res | bit;
            if (i==31) {
                break;
            }
            res = res << 1;
            n = n >> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();
        System.out.println(rb.reverseBits(-3));
    }


}
