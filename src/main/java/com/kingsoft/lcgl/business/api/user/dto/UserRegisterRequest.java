package com.kingsoft.lcgl.business.api.user.dto;

import com.kingsoft.lcgl.business.common.util.MD5Util;

/**
 * Created by yangdiankang on 2017/12/18.
 */
public class UserRegisterRequest {
    private String name;                                                //项目标题名
    private String mail;                                               //邮箱
    private String describe;                                           //项目描述
    private String departmentId[];                                    //参与部门的id
    private String pass;                                                //密码
    private String phone;
    private String identifyCode;                                       //验证码
    private String deparementIds;                                      //部门的id



    public String getDeparementIds() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<departmentId.length;i++){
            if(i!=0){
                stringBuilder.append(",");
            }
            stringBuilder.append(departmentId[i]);
        }
        return stringBuilder.toString();
    }

    public void setDeparementIds(String deparementIds) {
        this.deparementIds = deparementIds;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String[] getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String[] departmentId) {
        this.departmentId = departmentId;
    }

    public String getPass() {
        return MD5Util.getMD5(pass);
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }
}
