package com.cat.service.impl;

import com.cat.service.CatDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author wangxiaoqiang
 * @since 2018/08/31
 **/

@Service
public class CatDemoServiceImpl implements CatDemoService {
    @Override
    public String test(String str) {
        return "hello" + str;
    }
}
