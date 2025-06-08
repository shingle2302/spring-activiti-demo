package com.shingle.activiti.controller;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/process")
public class ProcessManagementController {

    @Autowired
    private ProcessEngine processEngine;

    // 挂起流程实例
    @PostMapping("/suspend/{processInstanceId}")
    public void suspendProcess(@PathVariable String processInstanceId) {
        processEngine.getRuntimeService()
                .suspendProcessInstanceById(processInstanceId);
    }

    // 激活流程实例
    @PostMapping("/activate/{processInstanceId}")
    public void activateProcess(@PathVariable String processInstanceId) {
        processEngine.getRuntimeService()
                .activateProcessInstanceById(processInstanceId);
    }
}
