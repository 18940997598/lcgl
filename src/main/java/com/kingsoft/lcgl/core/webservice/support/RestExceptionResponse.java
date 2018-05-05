package com.kingsoft.lcgl.core.webservice.support;

import java.io.Serializable;

/**
 * webservice异常实体类
 *
 * @author Jin Jiangxin <jinjiangxin@kingsoft.com>
 */
public class RestExceptionResponse implements Serializable {

    private Integer errorCode;           // 异常码errorCode
    private String errorMsg;             // 异常信息描述errorMsg

    public RestExceptionResponse(int code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
