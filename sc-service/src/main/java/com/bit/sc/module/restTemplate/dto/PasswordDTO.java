package com.bit.sc.module.restTemplate.dto;

import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-28 14:44
 */
@Data
public class PasswordDTO {
    /**
     * 组织机构id
     */
    private String orgId;
    /**
     * 设备ID列表
     */
    private List<String> deviceIdList;
    /**
     * 设备序列号列表
     */
    private List<String> deviceCdList;
    /**
     * 固定密码 md5加密结果
     */
    private String passwd;
}
