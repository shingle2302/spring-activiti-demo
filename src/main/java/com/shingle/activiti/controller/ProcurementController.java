package com.shingle.activiti.controller;

import com.shingle.activiti.service.ProcurementService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/procurement")
public class ProcurementController {

    @Autowired
    private ProcurementService procurementService;

    // 启动流程
    @PostMapping("/start")
    public String startProcess(@RequestParam double amount) {
        return procurementService.startProcess(amount);
    }

    // 完成指定任务
    @PostMapping("/complete")
    public void completeTask(
            @RequestParam String processInstanceId,
            @RequestParam String taskName,
            @RequestParam String assignee) {
        procurementService.completeTask(processInstanceId, taskName, assignee);
    }
}
