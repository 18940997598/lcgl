package com.kingsoft.lcgl.core.webservice.support;

import java.util.List;

/**
 * 通用多条返回实体
 *
 * @author Jin Jiangxin <jinjiangxin@kingsoft.com>
 */
public class PageResult<T> {

    private Long totalRecord;                               // 总条数
    private List<T> list;                                   // 详细信息列表

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
