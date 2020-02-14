package com.example.algotask;

public class ReverseStringByWord {
    // Given an input string, reverse the string word by word.
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        String[] arr = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = arr.length -1; i>=0; i--) {
            if (arr[i].trim().length() > 0) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(arr[i].trim());
            }
        }
        return sb.toString().trim();
    }


}
