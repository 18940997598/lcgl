package com.kingsoft.lcgl.core.webservice.exception;

/**
 * Created by yangdiankang on 2018/1/3.
 */
public class MyException extends Exception {
    private String name;

    public MyException(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
