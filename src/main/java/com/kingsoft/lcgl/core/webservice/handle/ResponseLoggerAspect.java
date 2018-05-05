package com.kingsoft.lcgl.core.webservice.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsoft.lcgl.business.common.HomeController;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * REST接口返回值日志拦截器
 * Created by wangyongkui
 */
@Aspect
@Component
public class ResponseLoggerAspect {

    private static final Logger log = Logger.getLogger(ResponseLoggerAspect.class);


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void requestMethod() {}//声明一个切入点


    @AfterReturning(pointcut="requestMethod()",returning = "result")
    public void afterReturnind(JoinPoint joinPoint, Object result) throws Throwable{
       if(joinPoint.getTarget().getClass() != HomeController.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(result);
            log.info("----------------- :"+"输出："+" out log start -----------------");
            log.info("Class: "+joinPoint.getTarget().getClass().getName()+" Method: "+joinPoint.getSignature().getName());
            log.info("response: " + message);
            log.info("----------------- :"+"输出："+" out log end -------------------");
        }
    }
}
