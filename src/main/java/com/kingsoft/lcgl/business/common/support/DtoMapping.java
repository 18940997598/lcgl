package com.kingsoft.lcgl.business.common.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DTO映射器注解
 * @author Liu Sixian
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DtoMapping {

    /**
     * 对应的实体字段
     * @return
     */
    String entityFiled() default "";

    /**
     * 对应的DTO字段
     * @return
     */
    String dtoField() default "";

    /**
     * 自定义字段转换器
     * @return
     */
    Class<DtoFieldConverter> converter() default DtoFieldConverter.class;

}
