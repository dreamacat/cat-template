package com.cat.event.service.impl;

import com.cat.event.service.CatDemoService;
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
