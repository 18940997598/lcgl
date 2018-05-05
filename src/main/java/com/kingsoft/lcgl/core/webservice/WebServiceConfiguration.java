package com.kingsoft.lcgl.core.webservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by wangyongkui on 2015/12/11.
 */
@Configuration
public class WebServiceConfiguration {

    @Bean(name="multipartResolver")
    public MultipartResolver mutipartResolver() {
        CommonsMultipartResolver com = new CommonsMultipartResolver();
        com.setMaxUploadSize(30*1024*1024);
        return com;
    }
}
