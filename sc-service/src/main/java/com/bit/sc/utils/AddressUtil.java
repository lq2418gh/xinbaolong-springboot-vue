package com.bit.sc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理地址code Util
 * @author liuyancheng
 * @create 2018-12-28 13:09
 */
public class AddressUtil {

    /**
     * 截取顶级小区code
     * @param str
     * @return
     */
    public static String sub(String str) {
        String aa = str.substring(1, str.length() - 1);
        String bb[] = aa.split("/");
        String temp = "";
        temp = "/" + temp + bb[0] + "/" + bb[1] + "/";
        return temp;
    }

    /**
     * 替换小区详情中所有空格，逗号等特殊符号
     * @param addressDetails
     * @return
     */
    public static String replaceAll(String addressDetails){
        String regEx="[\\-`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(addressDetails);
        return m.replaceAll("").trim();
    }
}
