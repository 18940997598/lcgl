package com.kingsoft.lcgl.business.api.project.service;


import com.kingsoft.lcgl.business.api.project.dto.NewProjectRequest;
import com.kingsoft.lcgl.business.api.project.dto.ProjectTaskRequest;
import com.kingsoft.lcgl.business.api.project.dto.testModel;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface ProjectService {

    CommonApiResponse addProject (NewProjectRequest request);

    CommonApiResponse addTask (ProjectTaskRequest request);


}
