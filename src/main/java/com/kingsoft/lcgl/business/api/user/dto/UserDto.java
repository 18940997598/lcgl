package com.kingsoft.lcgl.business.api.user.dto;

/**
 * Created by yangdiankang on 2018/1/3.
 */
public class UserDto {
    private Long id;
    private String name;
    private String pass;
    private String mail;
    private String phone;
    private String feiQ;
    private Long departmentId;

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFeiQ() {
        return feiQ;
    }

    public void setFeiQ(String feiQ) {
        this.feiQ = feiQ;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
