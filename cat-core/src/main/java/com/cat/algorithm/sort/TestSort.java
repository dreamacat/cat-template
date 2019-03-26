package com.cat.algorithm.sort;

/**
 * @author wangxiaoqiang
 * @since 2019/03/26
 **/
public class TestSort {
    public static Integer[] a = new Integer[]{3, 8, 5, 1, 4, 6, 2, 9, 7, 0};

    public static void main(String[] args) {
        Sort.shellSort(a);
        print(a);
    }

    public static void print(Integer[] a) {
        for (Integer integer : a) {
            System.out.print(integer + ",");
        }


    }

}
