package com.cat.algorithm.sort;

/**
 * @author wangxiaoqiang
 * @since 2019/03/26
 **/
public class Sort {
    /**
     * 插入排序
     * O(n*n)
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            T temp = a[p];
            for (j = p; j > 0 && temp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < a.length; i++) {
                T temp = a[i];
                for (j = i; j >= gap && temp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }





}
