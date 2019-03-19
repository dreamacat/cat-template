package com.cat.algorithm.lintcode;

import com.alibaba.fastjson.JSON;

/**
 * @author wangxiaoqiang
 * @since 2019/03/13
 **/
public class CombineAndSort {

    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public static int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int[] C = new int[A.length + B.length];
//        int n = a.length>b.length?a.length:b.length;

        int pc = 0;
        int pa = 0;
        int pb = 0;

        for (; pc < C.length; ) {

            if (pa < A.length && pb < B.length) {
                while (pa<A.length && pb<B.length&& A[pa] <= B[pb]) {
                    C[pc++] = A[pa++];
                }
                while (pa<A.length && pb<B.length && A[pa] > B[pb]) {
                    C[pc++] = B[pb++];
                }

            } else if (pa < A.length) {
                while (pa<A.length) {
                    C[pc++] = A[pa++];
                }
                break;
            } else {
                while (pb<B.length) {
                    C[pc++] = B[pb++];
                }
                break;
            }

        }
        return C;
    }

    public static void main(String[] args) {
        int c[] = mergeSortedArray(new int[]{7},new int[]{5,7});
        System.out.println(JSON.toJSONString(c));
    }
}
