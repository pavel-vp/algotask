package com.example.algotask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagramsIdx {
    // Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
    //
    //Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
    // TODO: Too long

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        char[] arrP = p.toCharArray();
        Arrays.sort(arrP);
        String ps = new String(arrP);

        for (int i = 0; i<s.length() - p.length() + 1; i++) {
            String t = s.substring(i, i + p.length());
            char[] arr = t.toCharArray();
            Arrays.sort(arr);
            String ts = new String(arr);
            if (ts.equals(ps)) {
                result.add(i);
            }
        }

        return result;
    }


}
