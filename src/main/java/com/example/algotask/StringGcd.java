package com.example.algotask;

public class StringGcd {

    /*
    For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)
    Return the largest string X such that X divides str1 and X divides str2.
     */

    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() == str2.length()) {
            if (str1.equals(str2)) {
                return str1;
            } else {
                return "";
            }
        }
        String smaller = null;
        String bigger = null;
        if (str1.length() < str2.length()) {
            smaller = str1;
            bigger = str2;
        } else {
            smaller = str2;
            bigger = str1;
        }
        for (int i = smaller.length(); i> 0; i--) {
            String s = smaller.substring(0, i);
            if (canBuild(bigger, s) && canBuild(smaller, s)) {
                return s;
            }
        }
        return "";
    }

    private boolean canBuild(String bigger, String s) {
        if (bigger.length() % s.length() > 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        int cnt = bigger.length() / s.length();
        for (int i = 0; i < cnt; i++) {
            sb.append(s);
        }
        return bigger.equals(sb.toString());
    }

    public static void main(String[] args) {
        StringGcd gcd = new StringGcd();
        System.out.println(gcd.gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX"));
    }

}
