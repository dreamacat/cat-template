package com.cat.json;

import com.alibaba.fastjson.JSON;
import com.cat.annotations.View;
import com.cat.event.service.CatDemoService;
import com.cat.event.service.LeaseCompanyService;
import com.cat.model.LeaseCompanyDO;
import com.cat.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    private LeaseCompanyService leaseCompanyServiceImpl;

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
//    @AuthZ
    public Result<LeaseCompanyDO> getLeaseCompany(@ApiParam(name="code", value="code") @RequestParam(name = "code", required = true) String code) {
        LeaseCompanyDO companyDO = leaseCompanyServiceImpl.getLeaseCompanyByCode(code);
        if (companyDO != null) {
            companyDO.setRate(1011);
        }
        System.out.println(leaseCompanyServiceImpl.updateByCode(companyDO));
        return Result.success(companyDO);
    }



}
