package com.cat.designpattern.decorator;

/**
 * @author wangxiaoqiang
 * @since 2018/10/19
 **/
public class TestDecorator {
    public void test() {
        try {
            this.getClass().getClassLoader().loadClass("com.cat.designpattern.decorator.Man");

        }catch (Exception e) {

        }
    }
    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA md1 = new ManDecoratorA();
        ManDecoratorB md2 = new ManDecoratorB();

        md1.setPerson(man);
        md2.setPerson(md1);
        md2.eat();


    }
}
