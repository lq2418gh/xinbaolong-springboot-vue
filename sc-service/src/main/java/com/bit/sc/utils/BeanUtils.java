package com.bit.sc.utils;

import com.bit.sc.module.restTemplate.dto.DeviceDTO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:  BeanUtils
 * @Author: liyujun
 * @Date: 2018-12-27
 **/
public class BeanUtils {

    /**
     * 判断bean里是否为空
     *
     * @param bean
     * @return
     */
    public static boolean checkFieldValueNull(Object bean) {
        boolean result = true;
        if (bean == null) {
            return true;
        }
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                if (fieldVal != null) {
                    if ("".equals(fieldVal)) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        return result;
    }


    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_'){

            startIndex = 1;
        }

        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否存在某属性的是否
     *
     * @param bean
     * @param fileName 属性
     * @return boolean
     */
    public static boolean checkFileExist(Object bean,String fileName) {

        boolean result = false;
        if (bean == null) {
            return result;
        }
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
             if(field.getName().equalsIgnoreCase(fileName)){
                 result=true;
                 return result;
             }
        }
        return result;
    }


    /**
     * 得到属性值
     * @param obj 所检验的bean
     * @param fileName 属性名
     */
    public static Object readAttributeValue(Object obj,String fileName){
        Object value=null;
        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                if(fileName.equals(name)){

                   value = field.get(obj);
                    return  value;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return  value;
    }

}
