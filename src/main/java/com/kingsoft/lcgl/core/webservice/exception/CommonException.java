package com.kingsoft.lcgl.core.webservice.exception;

/**
 * 系统公共异常对象
 *
 * @author: wangyongkui
 * Date: 14-2-27 上午10:55
 */
public class CommonException extends RuntimeException {

    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
