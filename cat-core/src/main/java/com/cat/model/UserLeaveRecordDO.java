package com.cat.model;

import lombok.Data;

import java.util.Date;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
@Data
public class UserLeaveRecordDO {
    private Long id;
    private String taskId;
    private String instanceId;
    private String userId;
    private Integer statusCode;
    private Integer nextStatus;
    private Integer result; // 0拒绝；1同意；2 审批中
    private String signResult; // 0拒绝；1同意；2 审批中
    private String content;
    private Date createTime;

    public boolean finished() {
        return !processing();
    }

    public boolean processing() {
        return result!= null && result!=0 && result!=1;
    }

    public boolean reject() {
        return result != null && result.equals(0);
    }


}
