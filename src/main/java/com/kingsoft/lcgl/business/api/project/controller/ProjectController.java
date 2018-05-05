package com.kingsoft.lcgl.business.api.project.controller;

import com.kingsoft.lcgl.business.api.project.dto.NewProjectRequest;
import com.kingsoft.lcgl.business.api.project.dto.ProjectTaskRequest;
import com.kingsoft.lcgl.business.api.project.dto.ResponeTestDto;
import com.kingsoft.lcgl.business.api.project.service.ProjectService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="new_project", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse newProject(@RequestBody NewProjectRequest request) {
        return  projectService.addProject(request);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="addProjectTask", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse projectTask(@RequestBody ProjectTaskRequest request) {
        return  projectService.addTask(request);
    }
}
