package com.bit.sc.module.equipment.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-18 19:42
 */
@Data
public class EquipmentAuthorizeVO {
    private List<Long> deviceIds;
    private Long groupId;
}
