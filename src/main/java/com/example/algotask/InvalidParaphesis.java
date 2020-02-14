package com.example.algotask;

import java.util.*;
import java.util.stream.Collectors;

public class InvalidParaphesis {
    // Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
    //
    //Note: The input string may contain letters other than the parentheses ( and )
    // Input: "(a)())()"
    //Output: ["(a)()()", "(a())()"]
    // FIXME: Too Much time (remove method)

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (isValid(s)) {
            result.add(s);
            return result;
        }

        Set<String> set = removeReq(s);
        int maxLength = 0;
        for (String s1 : set) {
            maxLength = Math.max(maxLength, s1.length());
        }

        int finalMaxLength = maxLength;
        result = set.stream()
                .filter(s2 -> s2.length() == finalMaxLength)
                .collect(Collectors.toList());

        if (result.size()==0) {
            result.add("");
        }
        return result;
    }
  // don't work properly
    private Set<String> removeReq(String s) {
        Set<String> result = new HashSet<>();
        if (isValid(s)) {
            result.add(s);
            return result;
        }
        String s0 = s.substring(0,1);
        String sLeft = s.substring(1);
        Set<String> setLeft = removeReq(sLeft);
        for (String s2 : setLeft) {
            if (isValid(s0+s2)) {
                result.add(s0+s2);
            }
        }
        if (result.size() == 0) {
            result.addAll(setLeft);
        }
        return result;
    }


    private Set<String> remove(String s) {
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        for (int i = 0; i< s.length(); i++) {
            String s2 = null;
            if (i == 0) {
                s2 = s.substring(1, s.length());
            } else {
                if (i == s.length() - 1) {
                    s2 = s.substring(0, s.length() - 1);
                } else {
                    s2 = s.substring(0, i) + s.substring(i+1, s.length());
                }
            }
            if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                sb.append(s.charAt(i));
            }
            if (isValid(s2)) {
                set.add(s2);
            } else {
                set.addAll(remove(s2));
            }
        }
        set.addAll(set);
        if (set.size()==0) {
            set.add(sb.toString());
        }
        return set;
    }

    private boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i< s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (ch == ')') {
                    if (stack.isEmpty()) return false;
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        InvalidParaphesis p = new InvalidParaphesis();
        System.out.println(p.removeInvalidParentheses("()())()"));
    }


}
