package com.shingle.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcurementService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    public String startProcess(double amount) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("amount", amount);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("procurementApprovalProcess", variables);
        return processInstance.getId();
    }

    public void completeTask(String processInstanceId, String taskName, String assignee) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(assignee)
                .taskName(taskName)
                .list();

        if (!tasks.isEmpty()) {
            taskService.complete(tasks.get(0).getId());
        }
    }
}
