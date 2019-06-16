package com.bit.sc.module.sys.vo;

import lombok.Data;

@Data
public class ResidentRelAddressActionVO {
    /**
     * 居民地址关系表id
     */
    private Long relId;
    /**
     * 居民地址关系表code
     */
    private String addressCode;
    /**
     * 更新操作类型 0-更新 1-删除  2-不变  3-新增
     */
    private Integer actionType;
}
