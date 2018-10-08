package com.cat.algorithm.optional;

import java.util.Optional;

/**
 * @author wangxiaoqiang
 * @since 2018/09/28
 **/
public class OptionalTest {

    public static Person createDefaultPerson() {
        System.out.println("new person");
        return new Person();
    }


    public static void main(String[] args) {
        Person person = new Person();
        System.out.println("----1");
        Optional.ofNullable(person).orElse(createDefaultPerson());

        System.out.println("----2");
        Optional.ofNullable(person).orElseGet(() -> createDefaultPerson());
    }
}
