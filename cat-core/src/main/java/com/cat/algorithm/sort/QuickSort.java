package com.cat.algorithm.sort;

/**
 * @author wangxiaoqiang
 * @since 2019/03/27
 **/
public class QuickSort {
    public static final int COUNT = 10;

    public static void quickSort(int a[]) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int a[], int left, int right) {

        if (left + COUNT <= right) {
            int mid = getPiovk(a, left, right);

        } else {
            insertSort(a, left, right);
        }

    }

    private static int getPiovk(int a[], int left, int right) {
        int center = (left + right) / 2;
        if (a[left] > a[right]) {
            swap(a, left, right);
        }
        if (a[left] <a[center]) {
            swap(a, left, center);
        }
        if(a[center]<a[right]) {
            swap(a, center, right);
        }
        swap(a, center, right-1);
        return a[right-1];
    }

    private static void swap(int a[], int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    private static void insertSort(int a[], int left, int right) {

    }
}
