package com.kingsoft.lcgl.business.api.common.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class TaskDto {
    private Long id;
    private String name;
    private String describe;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
