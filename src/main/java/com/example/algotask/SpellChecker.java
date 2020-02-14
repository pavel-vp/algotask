package com.example.algotask;

import java.util.*;

public class SpellChecker {
    // Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
    //
    //For a given query word, the spell checker handles two categories of spelling mistakes:
    //
    //Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
    //Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
    //Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
    //Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
    //Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
    //Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
    //Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
    //Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
    //In addition, the spell checker operates under the following precedence rules:
    //
    //When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
    //When the query matches a word up to capitlization, you should return the first such match in the wordlist.
    //When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
    //If the query has no matches in the wordlist, you should return the empty string.
    //Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].


    public String[] spellchecker(String[] wordlist, String[] queries) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> mapOriginal = new HashMap<>();

        int x = 0;
        for (String w : wordlist) {
            map.putIfAbsent(w.toLowerCase(), x);
            mapOriginal.put(w, x);
            x++;
        }

        String[] result = new String[queries.length];
        for (int i = 0; i< result.length; i++) {
            result[i] = "";
        }

        // Capitalzation
        for (int i = 0; i< queries.length; i++) {
            if (mapOriginal.containsKey(queries[i])) {
                result[i] = queries[i];
            } else {
                Integer idxOrig = map.get(queries[i].toLowerCase());
                if (idxOrig != null) {
                    result[i] = wordlist[idxOrig];
                }
            }
        }

        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
        Set<Character> vSet = new HashSet<>();
        for (char ch : vowels) {
            vSet.add(ch);
        }
        // Check Vowels
        for (int j = 0; j< queries.length; j++) {
            if (result[j].equals("")) {
                String q = queries[j];
                Set<String> comb = calcComb(q, vSet);

                Map<Integer, Integer> sortedMap = new TreeMap<>();

                for (String vc : comb) {
                        Integer idxOrig = map.get(vc.toLowerCase());
                        if (idxOrig != null) {
                            sortedMap.putIfAbsent(idxOrig, j);
                        }
                }

                for (Integer i : sortedMap.keySet()) {
                    if (result[sortedMap.get(i)].equals("")) {
                        result[sortedMap.get(i)] = wordlist[i];
                    }
                }
            }
        }



        return result;
    }

    private Set<String> calcComb(String q, Set<Character> vSet) {
        Set<String> set = new HashSet<>();

        int pos = -1;
        for (int i = 0; i< q.length(); i++) {
            if (vSet.contains( Character.toLowerCase(q.charAt(i)))) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            set.add(q);
            return set;
        }
        Set<String> leftSet = calcComb(q.substring(pos+1), vSet);

        for (Character v : vSet) {
            for (String left : leftSet) {
                String s = q.substring(0, pos) + v + left;
                set.add(s);
            }
        }

        return set;
    }

    public static void main(String[] args) {
        SpellChecker s = new SpellChecker();
        String[] wordlist = new String[] {"KiTe","kite","hare","Hare"};
        String[] queries = new String[] {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};

//        String[] wordlist = new String[] {"ae","aa"};
//        String[] queries = new String[] {"UU"};
        System.out.println(Arrays.toString(s.spellchecker(wordlist, queries)));
    }
}
