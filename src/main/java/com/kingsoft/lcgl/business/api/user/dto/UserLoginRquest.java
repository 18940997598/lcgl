package com.kingsoft.lcgl.business.api.user.dto;

import com.kingsoft.lcgl.business.common.util.MD5Util;

/**
 * Created by yangdiankang on 2018/1/3.
 */
public class UserLoginRquest {
    private String mail;
    private String pass;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return MD5Util.getMD5(pass);
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
