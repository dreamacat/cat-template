package com.cat.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangxiaoqiang
 * @since 2019/1/24.
 */
@AllArgsConstructor
@Getter
public enum LeaveEventEnum {
    APPLY_LEAVE("创建请假申请"),
    GROUP_ALLOW("组长同意申请"),
    GROUP_REJECT("组长拒绝申请"),
    BOSS_ALLOW("Boss同意申请"),
    BOSS_REJECT("Boss拒绝申请"),
    APPLY_CLOSE("请假流程结束");

    private String desc;
}


