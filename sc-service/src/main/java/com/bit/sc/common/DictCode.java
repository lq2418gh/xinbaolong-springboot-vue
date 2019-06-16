package com.bit.sc.common;
/**
 *
 * 功能描述:字典表的module描述
 *
 * @param:
 * @return:
 * @author: chenduo
 * @date: 2018/11/14 16:35
 */

public enum  DictCode {
    APP_TYPE(1, "app_type"), //app类型
    ACADEMICIAN(2, "ga_academician"),//学者
    COUNTRY_REGIONS(3, "ga_countryregions"),//国家或地区
    DOCUMENT(4, "ga_document"),//证件
    EDUCATION(5, "ga_education"),//教育背景
    HEALTH(6, "ga_health"),//健康状况
    HMTO(7, "ga_HMTO"),//港澳台人士
    JOB_STATUS(8, "ga_jobstatus"),//职位
    JOB_TYPE(9, "ga_jobtype"),//职业分类
    MARTIAL(10, "ga_marital"),//婚姻状况
    PEOPLE(11, "ga_people"),//民族
    POLITICAL(12, "ga_political"),//党派
    RELIGION(13, "ga_religion"),//宗教
    REPRESENTATIVE(14, "ga_representative"),//人大代表 政协委员
    SEX(15, "ga_sex");//性别


    private int code;
    private String info;

    DictCode(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }
}
