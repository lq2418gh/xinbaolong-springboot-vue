package com.bit.sc.module.manface.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ManfaceResidentEquipmentVO {


    private String equipmentName;
    private Long id;
    private String realName;
    private String cardId;
    private Date createTime;
    private Integer synchroStatus;
    private String deviceId;
    private Long attachmentId;
    private String attachmentPath;
    private String equipmentType;
    private String addressCode;
    private String addressDetail;
    private Long faceid;

}
