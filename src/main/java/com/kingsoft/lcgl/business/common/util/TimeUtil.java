package com.kingsoft.lcgl.business.common.util;


import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by yangdiankang on 2017/11/28.
 */
public class TimeUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    /**
     * 转时间戳
     */
    public static Long getTimeLong(String time){
        try {
            SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd" );
            Date date = format.parse(time);
            return date.getTime()/1000;
        }catch (Exception e){
            logger.info("转换时间戳报错"+"-------"+e);
        }
     return null;
    }

    public static String getTimeString(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(time));
    }
    /**
     * 转时间戳
     */
    public static Long timeLong(String time){
        try {
            SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            Date date = format.parse(time);
            Long dataTime  = date.getTime();
            return dataTime/1000;
        }catch (Exception e){
            logger.info("转换时间戳报错"+"-------"+e);
        }
        return null;
    }

    public static String getTimeStr(Long time){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分");
        return sdf.format(new Date(time*1000));
    }

}
