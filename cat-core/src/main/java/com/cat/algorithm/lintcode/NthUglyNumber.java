package com.cat.algorithm.lintcode;

/**
 * @author wangxiaoqiang
 * @since 2019/03/19
 **/
public class NthUglyNumber {

    public static void main(String[] args) {
        System.out.println(        nthUglyNumber(1665)
        );
    }
    /**
     * 1,2,3,4,5,6,8,9,10
     * -,-,-,-,-,2
     * -,-,-,3
     * -,-,5
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        // write your code here
        if (n==1) {
            return 1;
        }
        int count = 1;
        int i = 1;
        while (count != n) {
            if (check(++i)) {
                count++;
            }
        }
        return i;

    }

    public static boolean check(int n) {
        while (n>1) {
            if(n%5==0) {
                n=n/5;
                continue;
            }
            if(n%3==0) {
                n=n/3;
                continue;
            }
            if(n%2==0) {
                n=n/2;
                continue;
            }
            return false;
        }
        return  (n==1) ;
    }
}
