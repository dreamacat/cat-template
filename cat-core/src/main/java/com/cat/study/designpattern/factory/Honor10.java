package com.cat.study.designpattern.factory;

/**
 * @author wangxiaoqiang
 * @since 2018/11/27
 **/
public class Honor10 extends AbstractPhone {
    @Override
    public void getBrand() {
        System.out.println("华为荣耀10");
    }

    @Override
    public void getPrice() {
        System.out.println("2000大洋");
    }
}
