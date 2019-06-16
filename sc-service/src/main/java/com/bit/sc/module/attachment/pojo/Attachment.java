package com.bit.sc.module.attachment.pojo;

import java.util.Date;
import lombok.Data;

/**
 * Attachment
 * @author liuyancheng
 */
@Data
public class Attachment {

	//columns START

    /**
     * UUID
     */	
	private Long attachmentId;
    /**
     * 关联业务
     */	
	private Integer businessId;
    /**
     * 附件类型 0 图片
     */	
	private Integer attachmentType;
    /**
     * 附件类型
     */	
	private String attachmentSuffix;
    /**
     * 附件名称
     */	
	private String attachmentName;
    /**
     * 附件路径
     */	
	private String attachmentPath;
    /**
     * 附件创建时间
     */	
	private Date createTime;
    /**
     * 附件状态 1 启用  0 停用
     */	
	private Integer attachmentStatus;
    /**
     * 附件创建人ID
     */	
	private Long createUserId;
    /**
     * 附件创建人姓名
     */	
	private String createUsername;
    /**
     * 数据关联id
     */	
	private Long dataId;
    /**
     * 第三方接口返回id
     */
	private String fileId;

	//columns END
    //操作类型 0-更新 1-删除  2-不变  3-新增
    private Integer actionType;


}


