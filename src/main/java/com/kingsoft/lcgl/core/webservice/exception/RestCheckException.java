package com.kingsoft.lcgl.core.webservice.exception;


import com.kingsoft.lcgl.core.webservice.support.RestExceptionResponse;

/**
 * Created by wangyongkui on 15-2-12.
 */
public class RestCheckException extends RestException {

    public RestCheckException(int key) {
        super(key);
    }

    public RestCheckException(RestExceptionResponse response) {
        super(response);
    }

    public RestCheckException(int key, Throwable cause) {
        super(key,cause);
    }

    public RestCheckException(RestExceptionResponse response, Throwable cause) {
        super(response,cause);
    }
}
