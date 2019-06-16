package com.bit.sc.utils;


import java.util.Date;

public class RandomUtil {

    /**
     * 随机六位数字
     * @return
     */
    public static int randomSixNum(){
       return  (int)((Math.random()*9+1)*100000);
    }
    /**
     * 随机五位数字
     * @return
     */
    public static int randomFiveNum(){
        return  (int)((Math.random()*9+1)*10000);
    }
}
