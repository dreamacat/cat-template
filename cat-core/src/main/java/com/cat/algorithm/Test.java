package com.cat.algorithm;

import java.util.stream.LongStream;

/**
 * @author wangxiaoqiang
 * @since 2018/10/15
 **/
public class Test {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        sum2(10000000);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);

    }



    public static long printTime(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long :: sum);
    }

    public static long sum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long :: sum);
    }

    public static long sum2(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long :: sum);
    }



}
