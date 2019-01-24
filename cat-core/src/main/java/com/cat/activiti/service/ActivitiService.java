package com.cat.activiti.service;

import com.cat.constant.enums.ProcessKeyEnum;
import java.util.List;
import org.activiti.engine.task.Task;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
public interface ActivitiService {
    /**
     * 根据key启动流程
     *
     * @param keyEnum 流程key
     * @return
     */
    String startProcessByKey(ProcessKeyEnum keyEnum);

    /**
     * 根据key启动流程
     *
     * @param keyEnum 流程key
     * @param flag    分支名称
     * @return
     */
    String startProcessByKey(ProcessKeyEnum keyEnum, String flag);

    /**
     * 根据流程实例id获取当前任务
     *
     * @param instanceId
     * @return
     */
    Task findTaskByInstanceId(String instanceId);

    List<Task> getTaskListByInstanceId(String instanceId);

    /**
     * 根据任务id查询任务
     *
     * @param taskId
     * @return
     */
    Task findTaskByTaskId(String taskId);

    /**
     * 查询当前组的待处理任务
     *
     * @param groups
     * @return
     */
    List<Task> findTaskByGroup(List<String> groups);

    /**
     * 完成任务
     *
     * @param taskId
     * @param flag   控制该流程走向的标识
     */
    void completeTask(String taskId, Boolean flag);

    /**
     * 完成任务
     *
     * @param taskId
     * @param flag
     */
    void completeTask(String taskId, String flag);

    /**
     * 完成任务
     *
     * @param taskId
     */
    void completeTask(String taskId);

    /**
     * 删除特定任务
     *
     * @param instanceId
     * @param taskId
     * @param reason
     */
    void deleteTask(String instanceId, String taskId, String reason);

    /**
     * 删除特定任务
     *
     * @param instanceId
     * @param reason
     */
    void deleteProcessIns(String instanceId, String reason);


    /**
     *  回滚activiti 到执行的状态
     *
     * @param processInstanceId acitivi 实例ID
     * @param  definitionKey   状态Key  orderStatusEnum.name()
     */
    void taskRollback(String processInstanceId, String definitionKey);
}
