package com.cat.study.designpattern.factory;

/**
 * @author wangxiaoqiang
 * @since 2018/11/27
 **/
public abstract class AbstractPhone {
    public void printDes() {
        this.getBrand();
        this.getPrice();
    }
    public abstract void getBrand();
    public abstract void getPrice();
}
