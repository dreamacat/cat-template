package com.cat.json;

import com.alibaba.fastjson.JSON;
import com.cat.annotations.AuthZ;
import com.cat.annotations.View;
import com.cat.model.LeaseCompanyDO;
import com.cat.service.CatDemoService;
import com.cat.service.LeaseCompanyService;
import com.cat.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author wangxiaoqiang
 * @since 2018/08/31
 **/
@View
@Api(value = "CatDemo", description = "cat")
@Slf4j
public class CatDemoApi {

    @Autowired
    private CatDemoService catDemoService;
    @Resource
    private LeaseCompanyService leaseCompanyService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(value={"/hello"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="返回hello ", notes="返回hello + name")
    public Object hello(@ApiParam(name="name", value="姓名") @RequestParam(name = "name", required = true) String name) {
        String s = catDemoService.test(name);
        return JSON.toJSON(s);
    }

    @RequestMapping(value={"/testEvent"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="返回hello ", notes="返回hello + name")
    public Object testEvent() {
//        EntityVO entityVO = new EntityVO("hello", "cat");
//        applicationEventPublisher.publishEvent(entityVO);
        return JSON.toJSON("success");
    }


    @RequestMapping(value={"/getLeaseCompany"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="根据code查询 ", notes="返回hello + name")
    @AuthZ
    public Result<LeaseCompanyDO> getLeaseCompany(@ApiParam(name="code", value="code") @RequestParam(name = "code", required = true) String code) {
        LeaseCompanyDO companyDO = leaseCompanyService.getLeaseCompanyByCode(code);
        if (companyDO != null) {
            companyDO.setRate(1010);
        }
        System.out.println(leaseCompanyService.updateByCode(companyDO));
        return Result.success(companyDO);
    }



}
