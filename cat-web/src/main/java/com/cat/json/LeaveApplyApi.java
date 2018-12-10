package com.cat.json;

import com.cat.annotations.View;
import com.cat.model.UserLeaveRecordDO;
import com.cat.service.ActivitiService;
import com.cat.service.LeaveRecordService;
import com.cat.service.LeaveService;
import com.cat.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 * 只记录的流程状态，而没有驱动整个流程
 **/
@View
@Api(value = "leaveApply", description = "请假流程")
@Slf4j
public class LeaveApplyApi {

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private LeaveRecordService recordService;
    @Autowired
    private LeaveService leaveService;


    @RequestMapping(value={"/startApply"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="工作流处理流程 ", notes="返回结果")
    public Result<Boolean> startWorkflow(@ApiParam(name="userId", value="userId") @RequestParam(name = "userId", required = true) String userId) {
        UserLeaveRecordDO userLeaveRecordDO = recordService.getRecordByUserId(userId).stream().filter(UserLeaveRecordDO :: processing).findAny().orElse(null);
        Assert.isTrue(userLeaveRecordDO== null, "还有未处理完的申请");
        userLeaveRecordDO = new UserLeaveRecordDO();
        userLeaveRecordDO.setUserId(userId);
        leaveService.startLeaveFlow(userLeaveRecordDO);
        return Result.success(true);
    }


    @RequestMapping(value={"/signWorkFlow"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="处理请假 ", notes="返回审批结果")
    public Result<Boolean> signWorkFlow(@ApiParam(name="applyId", value="applyId") @RequestParam(name = "applyId", required = true) Long applyId,
                                     @ApiParam(name="agree", value="agree") @RequestParam(name = "agree", required = true) Integer agree) {

        if (agree != 0) {
            agree = 1 ;
        }
        UserLeaveRecordDO recordDOList =  recordService.getRecordById(applyId);
        Assert.notNull(recordDOList, "该申请记录不存在");
        leaveService.completeLeaveFlow(applyId, agree);

        return Result.success(agree==1);
    }



    @RequestMapping(value={"/getApplyTrace"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="查看当前进度 ", notes="查看当前进度")
    public Result<List<UserLeaveRecordDO>> getApplyTrace(@ApiParam(name="userId", value="userId") @RequestParam(name = "userId", required = true) String userId) {
        List<UserLeaveRecordDO> recordDOList =  recordService.getRecordByUserId(userId);
        return Result.success(recordDOList);
    }

}
