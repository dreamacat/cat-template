package com.cat.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangxiaoqiang
 * @since 2018/9/25.
 */
@AllArgsConstructor
@Getter
public enum EventTypeEnum {

    LEASE_COMPANY_ADD("添加"),
    LEASE_COMPANY_UPDATE("更新");

    private String desc;
}
