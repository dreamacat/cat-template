package com.cat.algorithm;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangxiaoqiang
 * @since 2018/09/27
 **/
public class SugarTest {

    public static void main(String[] args) {

        List<String> list = Lists.newArrayList("hello world","hello cat");
//        System.out.println("-------- map");
//        list.stream().map(s -> Arrays.stream(s.split("")).distinct().collect(Collectors.toList())).forEach(System.out::println);
//        System.out.println("------- flatap");
//        list.stream().flatMap(s -> Arrays.stream(s.split(""))).distinct().collect(Collectors.toList()).forEach(System.out::println);


//        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);

        /**相互组合**/
        List<String> list2 = Arrays.asList("hello", "hi", "你好");
        List<String> list3 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(da -> da.collect(Collectors.toList()).forEach(System.out::println));
        list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);

        //flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        //    map(Function<? super T, ? extends R> mapper);

//        List<String> list1 = null;
//        Optional<List<String>> op = Optional.ofNullable(list);
//        List<String> res = Optional.ofNullable(list1).orElse(list);
//
//        System.out.println(res == list);


        Optional.of("cat");

    }
}
