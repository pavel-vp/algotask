package com.example.algotask;

public class AddBinaryString {

    public String addBinary(String a, String b) {

        StringBuffer sbA = new StringBuffer();
        sbA.append(a);
        a = sbA.reverse().toString();
        StringBuffer sbB = new StringBuffer();
        sbB.append(b);
        b = sbB.reverse().toString();

        StringBuffer sb = new StringBuffer();

        int i = 0;
        int next = 0;
        while(i < a.length() || i < b.length()) {
            int sum = next;

            if (i < a.length()) {
                sum += a.charAt(i) - '0';
            }
            if (i < b.length()) {
                sum += b.charAt(i) - '0';
            }

            if (sum == 2) {
                sum = 0;
                next = 1;
            } else {
                if (sum == 3) {
                    sum = 1;
                    next = 1;
                } else {
                    next = 0;
                }
            }
            sb.append(Integer.toString(sum));
            i++;
        }
        if (next > 0) {
            sb.append(Integer.toString(next));
        }


        if(sb.length() == 0) return "0";
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinaryString s = new AddBinaryString();
        System.out.println(s.addBinary("11", "1"));

    }


}
