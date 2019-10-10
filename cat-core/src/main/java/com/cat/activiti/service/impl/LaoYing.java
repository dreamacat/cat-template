package com.cat.activiti.service.impl;

import com.cat.spi.IBird;

/**
 * @author wangxiaoqiang
 * @since 2019/04/09
 **/
public class LaoYing implements IBird {
    @Override
    public void eat() {
        System.out.println("老鹰吃食儿。。");
    }
}
