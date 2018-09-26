package com.cat.model;

import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
@Data
public class LeaseCompanyDO {
    private Long id;
    private String leaseCompanyCode;
    private String leaseCompanyName;
    private Integer rate;

}
