package com.kingsoft.lcgl.business.api.common.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class TaskRespone {
    private int code;
    private String msg;
    private List<TaskDto> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TaskDto> getData() {
        return data;
    }

    public void setData(List<TaskDto> data) {
        this.data = data;
    }
}
