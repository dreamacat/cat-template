package com.cat.algorithm.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangxiaoqiang
 * @since 2018/09/29
 **/
public class StreamTest {

    public static void main(String[] args) {

        List<Integer> num1 = Arrays.asList(1,2,3);
        List<Integer> num2 = Arrays.asList(3,4);

        List<int[]> pairs =
                num1.stream()
                .flatMap(i -> num2.stream().map(j->new int[]{i,j}))
                .collect(Collectors.toList());

    }

}
