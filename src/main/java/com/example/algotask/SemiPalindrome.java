package com.example.algotask;

public class SemiPalindrome {
    public boolean sPalin(String input){
        if (input == null || input.length() < 1) return false;
        int b = 0;
        int e = input.length() - 1;
        int cnt = 0;
        while (b < e) {
            char chB = input.charAt(b);
            char chE = input.charAt(e);
            if (chB == chE) {
                b++;
                e--;
                continue;
            }
            cnt++;
            if (cnt > 1) {
                return false;
            }
            char chB2 = input.charAt(b+1);
            char chE2 = input.charAt(e-1);
            if (chB == chE2) {
                e--;
            } else {
                if (chB2 == chE) {
                    b++;
                } else {
                    return false;
                }
            }

        }

        return true;
    }

    public static void main(String[] args) {
        SemiPalindrome sm = new SemiPalindrome();
        System.out.println(sm.sPalin("123"));
    }
}
