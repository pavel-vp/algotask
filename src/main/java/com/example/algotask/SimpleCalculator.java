package com.example.algotask;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleCalculator {

    // Implement a basic calculator to evaluate a simple expression string.
    //
    //The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

    public int calculate(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder num = new StringBuilder();
        boolean priorityOperation = false;
        for (int i = 0; i< s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') continue;
            if (ch >= '0' && ch <='9') {
                num.append(ch);
            } else {
                stack.push(num.toString());

                if (priorityOperation) {
                        String num2 = stack.pop();
                        String oper = stack.pop();
                        String num1 = stack.pop();
                        long res = resolve(num1, oper, num2);
                        stack.push(String.valueOf(res));
                }

                num = new StringBuilder();
                priorityOperation = (ch == '*' || ch == '/');
                stack.push(String.valueOf(ch));
            }
        }
        if (num.length() > 0) {
            stack.push(num.toString());
        }

        if (priorityOperation) {
            String num2 = stack.pop();
            String oper = stack.pop();
            String num1 = stack.pop();
            long res = resolve(num1, oper, num2);
            stack.push(String.valueOf(res));
        }
        while (stack.size() > 1) {
            String num1 = stack.pollLast();
            String oper = stack.pollLast();
            String num2 = stack.pollLast();
            long res = resolve(num1, oper, num2);
            stack.offerLast(String.valueOf(res));

        }
        int result = Integer.parseInt(stack.pollLast());
        return result;

    }

    private long resolve(String num1, String oper, String num2) {
        if ("+".equals(oper)) {
            return Long.parseLong(num1) + Long.parseLong(num2);
        }
        if ("-".equals(oper)) {
            return Long.parseLong(num1) - Long.parseLong(num2);
        }
        if ("*".equals(oper)) {
            return Long.parseLong(num1) * Long.parseLong(num2);
        }
        if ("/".equals(oper)) {
            return Long.parseLong(num1) / Long.parseLong(num2);
        }
        return 0;
    }

    public static void main(String[] args) {
        SimpleCalculator c = new SimpleCalculator();
        int res = c.calculate("0-2");
        System.out.println(res);
        res = c.calculate("1+2*3");
        System.out.println(res);
        res = c.calculate("1+2+3");
        System.out.println(res);
    }

}
