package com.bit.sc.module.resident.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResidentBatchAuthorizeVO {

    //选中的居民
    private List<Long> ids;
    //选中的组codes
    private String groupCode;
}
