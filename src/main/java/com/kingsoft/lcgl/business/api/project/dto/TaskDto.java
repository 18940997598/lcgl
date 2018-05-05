package com.kingsoft.lcgl.business.api.project.dto;

import com.kingsoft.lcgl.business.common.util.TimeUtil;

/**
 * Created by yangdiankang on 2018/1/19.
 */
public class TaskDto {
    private String taskName;
    private String datatime[];
    private String datatimeLong[];
    private String personName;
    private String personValue;
    private String describe;
    private Boolean isMail;

    //预计开始结束时间
    private Long startTime;
    private Long endTime;
    private Long departmentId;
    private Long userId;


    public String[] getDatatime() {
        return datatime;
    }

    public void setDatatime(String[] datatime) {
        this.datatime = datatime;
    }

    public String[] getDatatimeLong() {
        return datatimeLong;
    }

    public void setDatatimeLong(String[] datatimeLong) {
        this.datatimeLong = datatimeLong;
    }

    public Long getDepartmentId() {
        if(personValue.contains("/")){
            return Long.valueOf(personValue.split("/")[0]);
        }
        return Long.valueOf(personValue);
    }

    public Long getUserId() {
        if(personValue.contains("/")){
            return Long.valueOf(personValue.split("/")[1]);
        }
        return 0L;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonValue() {
        return personValue;
    }

    public void setPersonValue(String personValue) {
        this.personValue = personValue;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getStartTime() {
        System.out.println("dd"+datatime[0].toString());
        System.out.println("ddd"+datatimeLong[0].toString());
        return TimeUtil.getTimeLong(datatimeLong[0]);
    }

    public Long getEndTime() {
        return TimeUtil.getTimeLong(datatimeLong[1]);
    }

}
