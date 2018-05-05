package com.kingsoft.lcgl.core.webservice.authority;

import java.lang.annotation.*;

/**
 * Created by yangdiankang on 2017/12/29.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {
    /**
     * 权限的值
     * @return
     */
    public String value();

}
