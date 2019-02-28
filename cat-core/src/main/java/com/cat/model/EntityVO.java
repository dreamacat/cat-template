package com.cat.model;

import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
@Data
public class EntityVO {
    private String key;
    private String value;

    public EntityVO(String key, String val) {
        this.key = key;
        this.value = val;
    }

}
