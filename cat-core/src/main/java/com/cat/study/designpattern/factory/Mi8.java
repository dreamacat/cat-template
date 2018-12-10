package com.cat.study.designpattern.factory;

/**
 * @author wangxiaoqiang
 * @since 2018/11/27
 **/
public class Mi8 extends AbstractPhone {
    @Override
    public void getBrand() {
        System.out.println("小米8");
    }

    @Override
    public void getPrice() {
        System.out.println("1999大洋");
    }
}
