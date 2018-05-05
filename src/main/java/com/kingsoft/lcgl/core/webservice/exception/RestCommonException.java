package com.kingsoft.lcgl.core.webservice.exception;


import com.kingsoft.lcgl.core.webservice.support.RestExceptionResponse;

/**
 * Created by wangyongkui on 15-2-12.
 */
public class RestCommonException extends RestException {

    public RestCommonException(int key) {
        super(key);
    }

    public RestCommonException(RestExceptionResponse response) {
        super(response);
    }

    public RestCommonException(int key, Throwable cause) {
        super(key,cause);
    }

    public RestCommonException(RestExceptionResponse response, Throwable cause) {
        super(response,cause);
    }
}
