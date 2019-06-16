package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

/**
 * @author liuyancheng
 * @create 2018-12-04 14:06
 */
@Data
public class DeviceConfigDTO extends ResultDTO {
    /**
     * 配置类型
     */
    private String category;
    /**
     * 配置类型排序
     */
    private Integer categoryOrder;
    /**
     * 配置项名称
     */
    private String label;
    /**
     * 配置键
     */
    private String k;
    /**
     * 配置值
     */
    private String v;
    /**
     * 配置类型
     */
    private Integer type;
    /**
     * 配置默认值
     */
    private String defaultValue;
    /**
     * 配置项描述
     */
    private String desc;
    /**
     * 配置排序
     */
    private Integer sortOrder;
}
