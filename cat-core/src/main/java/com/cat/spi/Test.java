package com.cat.spi;

import java.util.ServiceLoader;

/**
 * java spi
 * 缺点：所有的实现类都会被加载进来，无论是否真的使用
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println(32&16);
        ServiceLoader<IBird> shouts = ServiceLoader.load(IBird.class);
        for (IBird bird : shouts) {
            bird.eat();
        }
    }



}
