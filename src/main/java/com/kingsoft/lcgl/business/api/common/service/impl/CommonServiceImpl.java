package com.kingsoft.lcgl.business.api.common.service.impl;


import com.kingsoft.lcgl.business.api.common.dto.DepartmentDto;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentPersonRespone;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentRespone;
import com.kingsoft.lcgl.business.api.common.dto.TaskRespone;
import com.kingsoft.lcgl.business.api.common.mapper.CommonMapper;
import com.kingsoft.lcgl.business.api.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonMapper commonMapper;
    @Override
    public DepartmentRespone getDepartments() {
        DepartmentRespone departmentRespone  = new DepartmentRespone();
        try{
            departmentRespone.setData(commonMapper.getDepartments());
            departmentRespone.setCode(0);
        }catch (Exception ex){
            departmentRespone.setCode(500);
        }
        return departmentRespone;
    }

    @Override
    public DepartmentPersonRespone commonGetDepartmentPerson() {
        DepartmentPersonRespone respone = new DepartmentPersonRespone();
        List<DepartmentDto> departmentDtoList =  commonMapper.getDepartments();
        for(DepartmentDto dto:departmentDtoList){
            dto .setData(commonMapper.getPersons(dto.getId()));
        }
        respone.setCode(0);
        respone.setData(departmentDtoList);
        respone.setMsg("success");
        return respone;
    }

    @Override
    public TaskRespone getTaskName() {
        TaskRespone respone = new TaskRespone();
        try {
            respone.setData(commonMapper.getTaskName());
            respone.setCode(0);
        } catch (Exception ex) {
            respone.setCode(500);
        }
        return respone;
    }
}
