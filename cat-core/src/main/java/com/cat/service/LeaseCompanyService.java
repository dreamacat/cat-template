package com.cat.service;

import com.cat.model.LeaseCompanyDO;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
public interface LeaseCompanyService {
    LeaseCompanyDO getLeaseCompanyByCode(String code);


    boolean updateByCode(LeaseCompanyDO companyDO);
}
