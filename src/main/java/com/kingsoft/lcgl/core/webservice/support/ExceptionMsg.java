package com.kingsoft.lcgl.core.webservice.support;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常信息类
 *
 * @author Jin Jiangxin <jinjiangxin@kingsoft.com>
 */
@Component
public class ExceptionMsg {

    public static final int SYSTEM_ERROR = 1;               // 系统错误
    public static final int AUTHORITY_ERROR = 2;            // api权限错误
    public static final int TOKEN_ERROR = 3;                // token错误
    public static final int IP_ERROR = 4;                   // ip错误
    public static final int TIMESTAMP_ERROR = 5;            // 时间验证错误
    public static final int PARAM_ERROR = 6;                // 请求参数错误
    public static final int GAME_CLIENT_USER_ERROR = 7;     //客户端用户错误




    private static Map<Integer, RestExceptionResponse> exMap = new HashMap<>();

    /**
     * 通过key值取得异常信息
     *
     * @param key
     * @return
     */
    public static RestExceptionResponse getRestExceptionResponse(int key) {
        return exMap.get(key);
    }

    /**
     * 初始化异常信息
     */
    @PostConstruct
    public void init() {

        // 系统级
        exMap.put(SYSTEM_ERROR, new RestExceptionResponse(SYSTEM_ERROR, "system server error"));
        exMap.put(AUTHORITY_ERROR, new RestExceptionResponse(AUTHORITY_ERROR, "authority error"));
        exMap.put(TOKEN_ERROR, new RestExceptionResponse(TOKEN_ERROR, "userToken error"));
        exMap.put(IP_ERROR, new RestExceptionResponse(IP_ERROR, "ip error"));
        exMap.put(TIMESTAMP_ERROR, new RestExceptionResponse(TIMESTAMP_ERROR, "request timestamp error"));
        exMap.put(PARAM_ERROR, new RestExceptionResponse(PARAM_ERROR, "request param error"));
        exMap.put(GAME_CLIENT_USER_ERROR, new RestExceptionResponse(GAME_CLIENT_USER_ERROR, "client user error"));


    }
}
