package com.example.algotask;

public class CountPalindromes {

    private boolean isPalindrome(String s) {
        if (s.length() == 1) return true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return  false;
            }
        }
        return true;
    }

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i< s.length(); i++) {
            for (int j = i+1; j< s.length()+1; j++) {
                String sub = s.substring(i, j);
                if (isPalindrome(sub)) {
                    count++;
                }
            }

        }
        return count;
    }

    public static void main(String[] args) {
        CountPalindromes cp = new CountPalindromes();
        System.out.println(cp.countSubstrings("aaa"));
    }



}
