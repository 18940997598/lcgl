package com.kingsoft.lcgl.core.webservice.exception;


import com.kingsoft.lcgl.core.webservice.support.ExceptionMsg;
import com.kingsoft.lcgl.core.webservice.support.RestExceptionResponse;

/**
 * Created by wangyongkui on 15-2-12.
 */
public class RestException extends CommonException {

    private RestExceptionResponse response;        // rest异常对象

    public RestExceptionResponse getResponse() {
        return response;
    }

    public void setResponse(RestExceptionResponse response) {
        this.response = response;
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(int key) {
        this.response = ExceptionMsg.getRestExceptionResponse(key);
    }

    public RestException(RestExceptionResponse response) {
        this.response = response;
    }

    public RestException(int key, Throwable cause) {
        super(cause);
        this.response = ExceptionMsg.getRestExceptionResponse(key);
    }

    public RestException(RestExceptionResponse response, Throwable cause) {
        super(cause);
        this.response = response;
    }

}
