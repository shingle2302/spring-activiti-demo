package com.shingle.activiti.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@RequestMapping("/api/diagram")
public class ProcessDiagramController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    // 获取流程图 PNG（根据流程实例 ID）
    @GetMapping(path = "/process/{processInstanceId}", produces = MediaType.IMAGE_PNG_VALUE)
    public void getProcessDiagram(@PathVariable String processInstanceId, HttpServletResponse response) throws Exception {
        String processDefinitionId = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult()
                .getProcessDefinitionId();

        InputStream imageStream = processEngine.getRepositoryService()
                .getProcessModel(processDefinitionId);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = imageStream.read(buffer)) != -1) {
            response.getOutputStream().write(buffer, 0, bytesRead);
        }
        imageStream.close();
    }
}
