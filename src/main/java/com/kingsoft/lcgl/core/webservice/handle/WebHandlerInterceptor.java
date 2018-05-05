package com.kingsoft.lcgl.core.webservice.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器
 * Created by yangdiankang on 2017/12/20.
 */
public class WebHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(WebHandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        logger.info(" ----------------- :输入： input log start -----------------");
        info(request);
        checkToken(request);
        logger.info(" ----------------- :输入： input log end -----------------");

        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     *检验token
     * @param
     * @param request
     */
    private void checkToken(HttpServletRequest request){
        String token  = request.getHeader("token");
        String refreshToken  = request.getHeader("refreshToken");

    }
    private void info(HttpServletRequest request) {
        // Address
        StringBuilder addressBuilder = new StringBuilder("地址：: ");
        addressBuilder.append(request.getScheme()).append("://");
        addressBuilder.append(request.getLocalAddr());
        if (request.getLocalPort() != 0) {
            addressBuilder.append(":").append(request.getLocalPort());
        }
        addressBuilder.append(request.getRequestURI());
        if (request.getMethod().equals("GET") && !StringUtils.isEmpty(request.getQueryString())) {
            addressBuilder.append("?").append(request.getQueryString());
        }
        logger.info(addressBuilder.toString());



        // IP
        String ip = request.getHeader("x-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        logger.info("IP：: "+ip);
    }

}
