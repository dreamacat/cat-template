package com.cat.study.designpattern.factory;

/**
 * @author wangxiaoqiang
 * @since 2018/11/27
 **/
public class PhoneFactory extends AbstractFactory {
    @Override
    public <T extends AbstractPhone> T createPhone(Class<T> c) {
        AbstractPhone phone = null;
        System.out.println("--- 开始生产 ---");
        try {
            phone = (AbstractPhone) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) phone;
    }
}
