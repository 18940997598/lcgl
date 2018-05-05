package com.kingsoft.lcgl.business.api.project.dto;

import com.kingsoft.lcgl.business.common.util.TimeUtil;

/**
 * Created by yangdiankang on 2017/12/18.
 */
public class NewProjectRequest {
    private Long id;
    private String name;                                                //项目标题名
    private String date[];                                              //
    private String describe;                                           //项目描述
    private String departmentId[];                                    //参与部门的id
    private String documentAddress;


    private  Long startDate;
    private  Long endDate;

    private String deparementIds;                                    //参与部门的ids




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

    public Long getId() {
        return id;
    }


    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
    }

    public void setId(Long id) {
        this.id = id;
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


}
