package com.bit.sc.module.restTemplate.dto;

import lombok.Data;

/**
 * @author liuyancheng
 * @create 2018-12-20 14:31
 */
@Data
public class RemoteCtrlInfo {
    private Integer ctrlCode;
    private Integer resultCode;
    private String capturedImgUri;
}
