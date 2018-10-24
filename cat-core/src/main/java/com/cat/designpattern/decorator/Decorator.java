package com.cat.designpattern.decorator;

/**
 * @author wangxiaoqiang
 * @since 2018/10/19
 **/
public abstract class Decorator implements Person {
    protected Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void eat() {
        person.eat();
    }
}
