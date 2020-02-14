package com.example.algotask;

public class ReverseSignedInt {
    /*
        Given a 32-bit signed integer, reverse digits of an integer.
        Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */
    public int reverse(int x) {
        int res = 0;
        int value = x;
        while (value != 0) {
            if ((res == Integer.MAX_VALUE && x > 0) || (res == Integer.MAX_VALUE - 1 && x < 0) ) {
                return 0;
            }
            int d = Math.abs(value % 10);

            if ((res > Integer.MAX_VALUE/10 && x > 0) || (res > (Integer.MAX_VALUE - 1)/10 && x < 0) ) {
                return 0;
            }

            if ((res == Integer.MAX_VALUE/10 && x > 0) || (res == (Integer.MAX_VALUE - 1)/10 && x < 0) ) {
                if ((x > 0 && d > Integer.MAX_VALUE % 10) || (x < 0 && d > (Integer.MAX_VALUE-1) % 10)) {
                    return 0;
                }
            }

            res = res * 10;
            res += d;
            value = (value) / 10;
        }
        return res * (x <0?-1:1);
    }

    public static void main(String[] args) {
        ReverseSignedInt rs = new ReverseSignedInt();
        System.out.println(rs.reverse(1463847412));
        System.out.println(rs.reverse(-123));
        System.out.println(rs.reverse(-2147483648));
    }

}
