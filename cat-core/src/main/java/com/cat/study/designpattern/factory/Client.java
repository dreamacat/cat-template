package com.cat.study.designpattern.factory;

/**
 * @author wangxiaoqiang
 * @since 2018/11/27
 **/
public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new PhoneFactory();
        AbstractPhone phone = factory.createPhone(Honor10.class);
        phone.printDes();

        AbstractPhone phone2 = factory.createPhone(Mi8.class);
        phone2.printDes();

    }
}
