package com.cat.designpattern.decorator;

/**
 * @author wangxiaoqiang
 * @since 2018/10/19
 **/
public class Man implements Person {

    @Override
    public void eat() {
        System.out.println("男人在吃");
    }
}