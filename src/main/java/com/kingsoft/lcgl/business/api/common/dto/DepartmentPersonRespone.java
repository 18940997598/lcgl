package com.kingsoft.lcgl.business.api.common.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class DepartmentPersonRespone {
    private int code;
    private String msg;
    private List<DepartmentDto> data;


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

    public List<DepartmentDto> getData() {

        return data;
    }

    public void setData(List<DepartmentDto> data) {
        this.data = data;
    }
}
