package com.cat.constant.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31.
 */

@AllArgsConstructor
public enum ProcessStatusEnum {
    APPLY_LEAVE(0, "申请请假",""),
    GROUP_SIGN(100, "待组长审批","提交申请"),
    BOSS_SIGN(200, "待boss审批", "组长审批通过"),
    APPLY_CLOSE(500, "审批结束", "审批结束");


    @Getter
    private Integer code;
    @Getter
    private String des;
    @Getter
    private String lastStatus;

    public static ProcessStatusEnum getByCode(int key) {
        return Arrays.stream(ProcessStatusEnum.values()).filter(data -> data.getCode().equals(key)).findAny().orElse(null);
    }

}
