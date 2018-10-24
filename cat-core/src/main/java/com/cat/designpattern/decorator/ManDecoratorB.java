package com.cat.designpattern.decorator;

/**
 * @author wangxiaoqiang
 * @since 2018/10/19
 **/
public class ManDecoratorB extends Decorator {

    @Override
    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }
}
