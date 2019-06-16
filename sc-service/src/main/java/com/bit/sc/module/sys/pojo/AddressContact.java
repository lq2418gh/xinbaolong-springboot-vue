package com.bit.sc.module.sys.pojo;

import lombok.Data;

/**
 * @author chenduo
 * @create 2018-12-28 10:32
 */
@Data
public class AddressContact {

    /**
     * id
     */
    private Long id;

    /**
     * 公安联系人
     */
    private String gaContactman;
    /**
     * 公安联系电话
     */
    private String gaContactmobile;
    /**
     * 社区联系人
     */
    private String sqContactman;
    /**
     * 社区联系电话
     */
    private String sqContactmobile;
    /**
     * 物业联系人
     */
    private String wyContactman;
    /**
     * 物业联系电话
     */
    private String wyContactmobile;
    /**
     * 小区id
     */
    private Long addressId;
}
