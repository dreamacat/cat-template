package com.cat.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31.
 */

@AllArgsConstructor
public enum ProcessKeyEnum {
    LEAVE_PROCESS("leave_process", "请假审批流程");

    @Getter
    private String key;
    @Getter
    private String des;

    public static ProcessKeyEnum getByKey(String key) {
        return Arrays.stream(ProcessKeyEnum.values()).filter(data -> data.getKey().equalsIgnoreCase(key)).findAny().orElse(null);
    }

}
