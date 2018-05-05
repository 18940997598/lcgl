package com.kingsoft.lcgl.business.common.util;

import java.util.Date;
import java.util.UUID;

/**
 * Created by yangdiankang on 2018/1/2.
 */
public class RandomUtil {
    /**
     * 生成六位随机数
     * @return
     */
    public static String getRandomSix(){

        return String.valueOf((int)Math.floor(Math.random() * (1000000 - 100000) + 100000));
    }
    /**
     * 生成token
     */
    public static String getToken(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    /**
     *规则： 邮箱+当前时间戳，进行MD5加密
     * 生成refreshToken
     */
    public static String getRefreshToken(String text){
        return MD5Util.getMD5(text+String.valueOf(new Date().getTime()));
    }

}
