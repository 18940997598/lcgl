package com.kingsoft.lcgl.business.api.common.controller;

import com.kingsoft.lcgl.business.api.common.dto.DepartmentPersonRespone;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentRespone;
import com.kingsoft.lcgl.business.api.common.dto.TaskRespone;
import com.kingsoft.lcgl.business.api.common.dto.TokenRespone;
import com.kingsoft.lcgl.business.api.common.service.CommonService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.core.webservice.authority.Authority;
import com.kingsoft.lcgl.core.webservice.exception.MyException;
import com.kingsoft.lcgl.business.api.common.service.EmailService;
import com.kingsoft.lcgl.core.webservice.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共方法类
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenService tokenService;

    /**
     * 获取部门信息
     * @return
     */
    @Authority(value = "department")
    @RequestMapping(value="commonGetDepartment", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DepartmentRespone commonGetDepartment() throws MyException {
            return  commonService.getDepartments();
    }


    /**
     * 获取 部门成员信息
     * @return
     * @throws MyException
     */
    @RequestMapping(value="commonGetDepartmentPerson", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DepartmentPersonRespone commonGetDepartmentPerson() throws MyException {
        return  commonService.commonGetDepartmentPerson();
    }

    /**
     * 获取任务名称
     * @return
     */
    @RequestMapping(value="commonGetTaskName", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskRespone commonGetTaskName() throws MyException {
        return  commonService.getTaskName();
    }

    /**
     *获取token
     * @return
     */
    @RequestMapping(value="commonGetToken", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TokenRespone commonGetToken(HttpServletRequest request ) {
        return tokenService.commonGetToken(request.getHeader("mail"));
    }
//
//    /**
//     *获取regreshToken
//     * @return
//     */
//    @RequestMapping(value="commonGetRegreshToken", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public String commonGetRegreshToken() {
//        CommonApiResponse commonApiResponse = new CommonApiResponse();
//        return  tokenService.commonGetRegreshToken();
//    }

}
