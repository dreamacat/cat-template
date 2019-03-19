package com.cat.algorithm.lintcode;

/**
 * @author wangxiaoqiang
 * @since 2019/03/13
 **/
public class RotateString {

    public static void main(String[] args) {
        rotateString(new char[]{'a', 'b', 'c', 'c', 'b', 'a'}, 3);

    }

    /**
     * 给定一个字符串（以字符数组的形式给出）和一个偏移量，根据偏移量原地旋转字符串(从左向右旋转)
     * 是向右偏移
     *
     * @param str
     * @param offset
     */
    public static void rotateString(char[] str, int offset) {
        if (str == null || str.length < 1 || offset < 0) {
            return;
        }
        offset = offset % str.length;
        reverseWords(str, str.length - offset, str.length - 1);
        reverseWords(str, 0, str.length - offset - 1);
        reverseWords(str, 0, str.length - 1);
    }

    private static void reverseWords(char[] result, int begin, int end) {
        while (begin < end) {
            char temp = result[begin];
            result[begin++] = result[end];
            result[end--] = temp;
        }
    }
}
