package com.kingsoft.lcgl.business.api.common.dto;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class PersonDto {
    private Long id;
    private String name;
    private String mail;


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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
