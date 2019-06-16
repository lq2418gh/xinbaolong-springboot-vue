package com.bit.sc.module.news.pojo;

import java.util.Date;
import lombok.Data;

/**
 * AddressRelAnnouncement
 * @author chenduo
 */
@Data
public class AddressRelAnnouncement {

	//columns START

    /**
     * 主键id
     */	
	private Long id;
    /**
     * 小区id
     */	
	private Long addressId;
    /**
     * 新闻id
     */	
	private Long announcementId;

	//columns END
    /**
     * 更新操作类型 0-更新 1-删除  2-不变  3-新增
     */
    private Integer actionType;

}


