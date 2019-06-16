package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-17 13:08
 */
@Data
public class CallBackDTO extends ResultDTO {
    /**
     * 回调POST的完整URL
     */
    private String callbackUrl;
    /**
     * 回调类别
     */
    private Integer callbackTag;

    /**
     * 组织ID
     */
    private String orgId;
}
