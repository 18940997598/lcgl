package com.kingsoft.lcgl.business.api.user.dto;

import com.kingsoft.lcgl.business.common.util.TimeUtil;

/**
 * Created by yangdiankang on 2017/12/18.
 */
public class UserRegistRequest {
    private String name;                                                //项目标题名
    private String date[];                                              //
    private String describe;                                           //项目描述
    private String departmentId[];                                    //参与部门的id
    private int open;                                               //任务公开程度
    private String openDepartmentId[];                                 //项目公开部门id,(只有部分公开的时候才会出现)

    private  Long startDate;
    private  Long endDate;

    private String deparementIds;                                    //参与部门的ids
    private String openDepartmentIds;                               //公开部门的ids


    public String getOpenDepartmentIds() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<openDepartmentId.length;i++){
            if(i!=0){
                stringBuilder.append(",");
            }
            stringBuilder.append(openDepartmentId[i]);
        }
        return stringBuilder.toString();
    }

    public void setOpenDepartmentIds(String openDepartmentIds) {
        this.openDepartmentIds = openDepartmentIds;
    }

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

    public Long getStartDate() {
        return TimeUtil.getTimeLong(date[0]);
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return TimeUtil.getTimeLong(date[1]);
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
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

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public String[] getOpenDepartmentId() {
        return openDepartmentId;
    }

    public void setOpenDepartmentId(String[] openDepartmentId) {
        this.openDepartmentId = openDepartmentId;
    }
}
