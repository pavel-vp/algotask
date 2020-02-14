package com.example.algotask;

public class CheckIfFactorial {
    private static boolean isFactorial(Integer n){
        if (n == 1) return true;
        long f = 1;
        for (int x = 1; x< n; x++) {
            f = f * x;
            if (f < n && (n % x == 0)) {
                continue;
            }
            if (f == n) return true;
            if (f > n || (n % x > 0)) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(CheckIfFactorial.isFactorial(1));
        System.out.println(CheckIfFactorial.isFactorial(0));
        System.out.println(CheckIfFactorial.isFactorial(6));
        System.out.println(CheckIfFactorial.isFactorial(39916800));
        System.out.println(CheckIfFactorial.isFactorial(765765));

    }
}
