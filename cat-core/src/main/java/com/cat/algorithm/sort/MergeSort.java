package com.cat.algorithm.sort;

/**
 * @author wangxiaoqiang
 * @since 2019/03/26
 **/
public class MergeSort {

    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T [] tempArray = (T [])new Comparable [a.length];
        mergeSort(a, tempArray, 0, a.length);
    }
    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tempArray, int left, int right ) {
        if (left <right) {
            int center = (left+right)/2;
            mergeSort(a, tempArray, left, center);
            mergeSort(a, tempArray, center+1, right);
            merge(a, tempArray, left, center, right);

        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tempArray, int left, int center, int right ) {

    }


}
