package com.example.algotask;

public class FindBadVersion {

    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;
        int mid = (right - left)/2;
        while((right - left) > 1) {
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
            mid = left + (right - left)/2;
        }
        if (isBadVersion(left)) {
            return left;
        } else {
            return right;
        }
    }

    private boolean isBadVersion(int s) {
        if (s >= 4) return true;
        return false;
    }

    public static void main(String[] args) {
        FindBadVersion f = new FindBadVersion();
        int n = f.firstBadVersion(7);
        System.out.println(n);
    }
}
