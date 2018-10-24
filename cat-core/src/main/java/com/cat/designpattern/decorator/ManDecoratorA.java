package com.cat.designpattern.decorator;

/**
 * @author wangxiaoqiang
 * @since 2018/10/19
 **/
public class ManDecoratorA extends Decorator {

    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        System.out.println("再吃一顿饭");
    }
}
