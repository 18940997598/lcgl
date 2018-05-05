package com.kingsoft.lcgl.core.redis.util;

/**
 * redis 存储枚举类
 * Created by yangdiankang on 2018/1/5.
 */
public enum  Jedis {
    TOKEN("lcgl:token:"), REFRESHTOKEN("lcgl:refreshToken:"), MAIL("lcgl:mail:"), IDENTIFYCODE("lcgl:identifyCode:");
    Jedis(String name) {
        this.name = name;
    }
    private String name ;
    public String getName() {
        return name;
    }
}
