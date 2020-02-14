package com.example.algotask;

public class ShortestSupersequence {
    // Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.
    //
    //(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)

    // Input: str1 = "abac", str2 = "cab"
    //  Output: "cabac"

    private boolean isSubseq(String s, String sub) {
        int i = 0;
        int j = 0;
        int cnt = 0;
        for (; i< s.length(); i++) {
            if (s.charAt(i) == sub.charAt(j)) {
                j++;
                cnt++;
            } else {

            }
        }
        if (cnt == sub.length()) {
            return true;
        } else {
            return false;
        }
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        if (str1.equals(str2)) return str1;

        String s = str1;
        String sub = str2;
        if (str1.length() < str2.length() ) {
            s = str2;
            sub = str1;
        }
        String minSuper = s + sub;


        return minSuper;
    }

    public static void main(String[] args) {
        ShortestSupersequence s = new ShortestSupersequence();
        System.out.println(s.isSubseq("abac", "aac"));
    }

}
