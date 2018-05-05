package com.kingsoft.lcgl.business.api.project.service.impl;

import com.kingsoft.lcgl.business.api.project.controller.ProjectController;
import com.kingsoft.lcgl.business.api.project.dto.NewProjectRequest;
import com.kingsoft.lcgl.business.api.project.dto.ProjectTaskRequest;
import com.kingsoft.lcgl.business.api.project.dto.TaskDto;
import com.kingsoft.lcgl.business.api.project.dto.testModel;
import com.kingsoft.lcgl.business.api.project.mapper.ProjectMapper;
import com.kingsoft.lcgl.business.api.project.service.ProjectService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public CommonApiResponse addProject(NewProjectRequest request) {
        CommonApiResponse commonApiResponse = new CommonApiResponse();
        try{

            projectMapper.addProject(request);
            commonApiResponse.setCode(0);
            commonApiResponse.setData(request.getId().toString());
        }catch (Exception e){
            commonApiResponse.setCode(500);
            commonApiResponse.setMsg(e.toString());
        }
        return commonApiResponse;
    }

    @Override
    public CommonApiResponse addTask(ProjectTaskRequest request) {
        CommonApiResponse response  = new CommonApiResponse();
        if (request.getMail()){
            this.sendMail(request);
        }

      List<TaskDto> list = request.getDataSourse();
        System.out.println(request.getProjectId().toString()+"--"+request.getMail());
        for(TaskDto dto:list){
            System.out.println(dto.getTaskName()+"-"+dto.getStartTime().toString()+"-"+dto.getEndTime().toString()+"-"+dto.getDescribe()+"-"+dto.getDepartmentId().toString()+"-"+dto.getUserId().toString());
        }
            projectMapper.addTask(request.getDataSourse(),request.getProjectId());

        response.setCode(0);
        return response;
    }

    /**
     * 发送邮件
     * @param request
     */
    private void sendMail(ProjectTaskRequest request) {

    }
}
