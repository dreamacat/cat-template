package com.cat.algorithm.optional;

import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2018/09/28
 **/
public class Person {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
