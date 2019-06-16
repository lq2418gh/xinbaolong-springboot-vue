package com.bit.sc.utils;

import java.util.Date;

/**
 * 统一日期处理类,
 * 不要直接在代码中引入其它第三方日期处理类,
 * 统一使用这里的
 */
public class DateUtil {

    /**
     * 字符串转日期
     * DateUtil.parse方法会自动识别一些常用格式，包括：
     *
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd
     * HH:mm:ss
     * yyyy-MM-dd HH:mm
     * yyyy-MM-dd HH:mm:ss.SSS
     * @param dateStr
     * @return
     */
    public static Date parse(String dateStr){
        Date date = cn.hutool.core.date.DateUtil.parse(dateStr);
        return date;
    }

    public static void main(String[] args) {
        String dateStr = "2017-03-01";
        Date date = parse(dateStr);
    }
}
