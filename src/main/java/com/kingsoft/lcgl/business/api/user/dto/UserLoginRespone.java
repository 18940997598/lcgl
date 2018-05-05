package com.kingsoft.lcgl.business.api.user.dto;

/**
 * Created by yangdiankang on 2018/1/3.
 */
public class UserLoginRespone {
    private  int code = 0;
    private  String msg;
    private  String token;
    private  String refreshToken;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
