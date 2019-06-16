package com.bit.sc.module.accessrecord.vo;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

@Data
public class AccessRecordVo extends BasePageVo {
    //表名
    private String tableName;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //地址
    private String address;
    //地址
    private Integer status;
    //身份证号
    private String idCard;
    //姓名
    private String name;




}
