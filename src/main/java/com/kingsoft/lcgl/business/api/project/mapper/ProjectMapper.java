package com.kingsoft.lcgl.business.api.project.mapper;

import com.kingsoft.lcgl.business.api.project.dto.NewProjectRequest;
import com.kingsoft.lcgl.business.api.project.dto.TaskDto;
import com.kingsoft.lcgl.business.api.project.dto.testModel;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface ProjectMapper {


    void addProject(NewProjectRequest request);

    void addTask(@Param("taskList") List<TaskDto> taskList,@Param("projectId") Long projectId);

}
