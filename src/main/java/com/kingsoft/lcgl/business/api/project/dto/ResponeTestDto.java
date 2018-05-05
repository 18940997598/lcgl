package com.kingsoft.lcgl.business.api.project.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class ResponeTestDto {
   private List<testModel>  listDto;

    public List<testModel> getListDto() {
        return listDto;
    }

    public void setListDto(List<testModel> listDto) {
        this.listDto = listDto;
    }
}
