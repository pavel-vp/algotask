package com.example.algotask;

import java.util.ArrayList;
import java.util.List;

public class MajorElemN3 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1000441);
        list.add(1000441);
        list.add(1000994);
        int res = repeatedNumber(list);
        System.out.println(res);
    }

    public static int repeatedNumber(final List<Integer> a) {
        // 6/3 = 2

        // 1,3,7,4,7,5
        //     |
        // cand1=1 cnt=0
        // cand2=3 cnt=0

        Integer m1=null;
        int cnt1=0;
        Integer m2=null;
        int cnt2=0;
        for (Integer v: a) {
            if (m1!=null && m1.equals(v)) {
                cnt1++;
            } else if (m2 != null && m2.equals(v)) {
                cnt2++;
            } else if (m1==null) {
                m1=v;
                cnt1=1;
            } else if (m2==null) {
                m2=v;
                cnt2=1;
            } else {
                cnt1--;
                cnt2--;
                if (cnt1 == 0) {
                    m1=null;
                }
                if (cnt2 == 0) {
                    m2=null;
                }
            }
        }
        cnt1=0;
        cnt2=0;
        for (Integer v: a) {
            if (m1 != null && m1.equals(v)) {cnt1++;}
            if (m2 != null && m2.equals(v)) {cnt2++;}
        }
        if (cnt1 > a.size()/3) return m1;
        if (cnt2 > a.size()/3) return m2;
        return -1;

    }

}
