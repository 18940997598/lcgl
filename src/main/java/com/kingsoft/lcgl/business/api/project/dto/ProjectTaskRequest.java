package com.kingsoft.lcgl.business.api.project.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/1/19.
 */
public class ProjectTaskRequest {
    private Long projectId;
    private Boolean mail;
    private String mailContent;

    private List<TaskDto> dataSourse;


    public Boolean getMail() {

        return mail;
    }

    public void setMail(Boolean mail) {
        this.mail = mail;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }



    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }




    public List<TaskDto> getDataSourse() {
        return dataSourse;
    }

    public void setDataSourse(List<TaskDto> dataSourse) {
        this.dataSourse = dataSourse;
    }
}
