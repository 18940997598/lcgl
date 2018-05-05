package com.kingsoft.lcgl.core.webservice.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * rest参数异常类
 *
 * @author Jin Jiangxin <jinjiangxin@kingsoft.com>
 */
public class RestParamsException extends RestException {

    private List<FieldError> errors;

    public List<FieldError> getErrors() {
        return errors;
    }

    public RestParamsException(String message) {
        super(message);
    }

    public RestParamsException(List<FieldError> errors) {
        super("参数校验错误");
        this.errors = errors;
    }
}
