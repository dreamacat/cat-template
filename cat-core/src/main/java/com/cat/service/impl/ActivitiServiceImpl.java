package com.cat.service.impl;

import com.cat.constant.enums.ProcessKeyEnum;
import com.cat.service.ActivitiService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxiaoqiang
 * @since 2018/10/31
 **/
@Slf4j
@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;

    private final String checkVariables = "check";


    @Override
    public String startProcessByKey(ProcessKeyEnum keyEnum) {
        return startProcessByKey(keyEnum, null);
    }

    @Override
    public String startProcessByKey(ProcessKeyEnum keyEnum, String flag) {
        String processKey = keyEnum.getKey();
        Map<String, Object> variableMap = null;
        if (flag != null) {
            variableMap = Maps.newHashMap();
            variableMap.put(checkVariables, flag);
        }
        log.info("工作流开始，processKey = " + processKey);
        return runtimeService.startProcessInstanceByKey(processKey, processKey, variableMap).getId();
    }

    @Override
    public Task findTaskByInstanceId(String instanceId) {
        return taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
    }
    @Override
    public List<Task> getTaskListByInstanceId(String instanceId) {
        List<Task> tasks = ((TaskQuery)this.taskService.createTaskQuery().processInstanceId(instanceId)).list();
        return tasks;
    }

    @Override
    public Task findTaskByTaskId(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public List<Task> findTaskByGroup(List<String> groups) {
        return taskService.createTaskQuery().taskCandidateGroupIn(groups).list();
    }

    @Override
    public void completeTask(String taskId, Boolean flag) {
        Map<String, Object> variableMap = null;
        if (flag != null) {
            variableMap = new HashMap<>();
            variableMap.put(checkVariables, flag);
        }
        taskService.complete(taskId, variableMap);
        log.info("工作流完成");
    }

    @Override
    public void completeTask(String taskId, String flag) {
        Map<String, Object> variableMap = null;
        if (flag != null) {
            variableMap = new HashMap<>();
            variableMap.put(checkVariables, flag);
        }
        taskService.complete(taskId, variableMap);
        log.info("工作流完成");

    }

    @Override
    public void completeTask(String taskId) {
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put(checkVariables, true);
        taskService.complete(taskId, variableMap);
        log.info("工作流完成");

    }

    @Override
    public void deleteTask(String instanceId, String taskId, String reason) {
        try {
            runtimeService.deleteProcessInstance(instanceId, reason);
            taskService.deleteTask(taskId, reason);
        } catch (Exception e) {
            log.warn("删除任务失败，instance id: " + instanceId + ", task id:" + taskId);
            throw new RuntimeException("删除任务失败：" +instanceId );
        }
    }

    @Override
    public void deleteProcessIns(String instanceId, String reason) {
        try {
            runtimeService.deleteProcessInstance(instanceId, reason);
        } catch (Exception e) {
            log.warn("删除任务失败，instance id: " + instanceId);
            throw new RuntimeException("删除任务失败：" +instanceId );
        }
    }


    /**
     *  回滚activiti 到执行的状态
     *
     * @param processInstanceId acitivi 实例ID
     * @param  definitionKey   状态Key  orderStatusEnum.name()
     */
    @Override
    public void taskRollback(String processInstanceId,String definitionKey){

        //进而获取流程实例
//        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
//
//        //获得流程定义
//        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(instance.getProcessDefinitionId());
//        Asserts.notNull(definition, definitionKey +" not exists ActivitiTask ");
//
//        // 获取流程层节点定义
//        ActivityImpl activitiImpl =  definition.findActivity(definitionKey);
//        Asserts.notNull(activitiImpl, definitionKey +"  所对应的流程节点不存在 " );
//        managementService.executeCommand(new JumpCmd(instance.getId(),activitiImpl.getId()));

    }

//    class JumpCmd implements Command<ExecutionEntity> {
//
//        private String processInstanceId;
//        private String activityId;
//        public static final String REASION_DELETE = "deleted";
//
//        public JumpCmd(String processInstanceId, String activityId) {
//            this.processInstanceId = processInstanceId;
//            this.activityId = activityId;
//        }
//
//        @Override
//        public ExecutionEntity execute(CommandContext commandContext) {
//            ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findby(processInstanceId);
//
//            executionEntity.destroyScope(REASION_DELETE);
//            ProcessDefinitionImpl processDefinition = executionEntity.getProcessDefinition();
//            ActivityImpl activity = processDefinition.findActivity(activityId);
//            executionEntity.executeActivity(activity);
//
//            return executionEntity;
//        }
//
//    }
}
