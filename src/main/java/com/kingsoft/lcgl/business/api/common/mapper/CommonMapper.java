package com.kingsoft.lcgl.business.api.common.mapper;

import com.kingsoft.lcgl.business.api.common.dto.DepartmentDto;
import com.kingsoft.lcgl.business.api.common.dto.PersonDto;
import com.kingsoft.lcgl.business.api.common.dto.TaskDto;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface CommonMapper {

    List<DepartmentDto> getDepartments();

    List<PersonDto> getPersons(@Param("departmentId")Long departmentId);

    List<TaskDto> getTaskName();
}
