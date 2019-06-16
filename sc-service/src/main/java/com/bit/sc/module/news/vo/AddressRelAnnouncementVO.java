package com.bit.sc.module.news.vo;

import java.util.Date;
import java.util.List;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * AddressRelAnnouncement
 * @author chenduo
 */
@Data
public class AddressRelAnnouncementVO extends BasePageVo{

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
     * 是否发布：0 ：发布，1：未发布
     */
    private Long isPublish;
    /**
     * 是否删除：0：可用， 1：删除
     */
    private Long isDelete;
    /**
     * 是否横幅 0-是 1-不是
     */
    private Long isBanner;
    /**
     * 小区ids
     */
    private List<Long> addressids;

}


