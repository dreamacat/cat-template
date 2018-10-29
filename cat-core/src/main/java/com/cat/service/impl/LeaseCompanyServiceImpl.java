package com.cat.service.impl;

import com.cat.constant.enums.EventTypeEnum;
import com.cat.dao.user.LeaseCompanyDao;
import com.cat.listener.AsyncEventEntity;
import com.cat.model.LeaseCompanyDO;
import com.cat.service.LeaseCompanyService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
@Service
@Slf4j
public class LeaseCompanyServiceImpl implements LeaseCompanyService {

    @Autowired
    private LeaseCompanyDao leaseCompanyDao;

    @Autowired

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public LeaseCompanyDO getLeaseCompanyByCode(String code) {
        Map<String, String> param = Maps.newHashMap();
        param.put("leaseCompanyCode", code);
        LeaseCompanyDO leaseCompanyDO = leaseCompanyDao.findLeaseCompanyProperty(param);
        return leaseCompanyDO;
    }

    @Override
    @Transactional
    public boolean updateByCode(LeaseCompanyDO companyDO) {
        int res = leaseCompanyDao.updateByCode(companyDO);
        applicationEventPublisher.publishEvent(new AsyncEventEntity(companyDO,EventTypeEnum.LEASE_COMPANY_ADD));

        System.out.println("事件发送成功");
        return res == 0;
    }
}
