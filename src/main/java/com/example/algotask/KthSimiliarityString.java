package com.example.algotask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KthSimiliarityString {
    //Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
    //
    //Given two anagrams A and B, return the smallest K for which A and B are K-similar.
    // A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
    Map<String, Integer> map = new HashMap<>();
    public int kSimilarity(String A, String B) {

        if (A.equals(B)) return 0;
        if (map.containsKey(A+"_"+B)) {
            return map.get(A+"_"+B);
        }
        int pos = 0;
        while (pos < A.length() && A.charAt(pos) == B.charAt(pos)) {
            pos++;
        }
        if (pos == A.length()) return 0;
        char b = B.charAt(pos);

        List<Integer> possiblePos = new ArrayList<>();
        int posBinA = pos;
        while(posBinA < A.length()) {
            if (A.charAt(posBinA) == b) {
                possiblePos.add(posBinA);
            }
            posBinA++;
        }

        int min = Integer.MAX_VALUE;
        for (Integer posssible : possiblePos) {
            char[] charA = A.toCharArray();
            charA[posssible] = charA[pos];
            charA[pos] = b;

            String newA = new String(charA).intern();

            int res = kSimilarity(newA.substring(pos + 1), B.substring(pos + 1));
            if (res < min) {
                min = res;
            }

        }
        map.putIfAbsent(A+"_"+B, 1+ min);
        return 1 + min;
    }

    public static void main(String[] args) {
        KthSimiliarityString k = new KthSimiliarityString();
     //   System.out.println(k.kSimilarity("bcac","cbca"));
        System.out.println(k.kSimilarity("abccaacceecdeea","bcaacceeccdeaae"));
    }

}
