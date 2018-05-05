package com.kingsoft.lcgl.business.api.common.service;


import com.kingsoft.lcgl.business.api.common.dto.DepartmentDto;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentPersonRespone;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentRespone;
import com.kingsoft.lcgl.business.api.common.dto.TaskRespone;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface CommonService {

    DepartmentRespone getDepartments();

    DepartmentPersonRespone commonGetDepartmentPerson();

    TaskRespone getTaskName();
}
