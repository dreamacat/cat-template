package com.cat.algorithm.optional;

import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2018/09/28
 **/
@Data
public class Insurance {
    private String name;
    private Integer type;

    public Insurance() {

    }

    public Insurance(String name, Integer type) {
        this.name = name;
        this.type = type;
    }
}
