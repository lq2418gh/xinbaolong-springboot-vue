package com.bit.sc.module.news.pojo;

import java.util.Date;
import lombok.Data;

/**
 * Announcement
 * @author zzk
 */
@Data
public class Announcement {

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * title
     */	
	private String title;
    /**
     * content
     */	
	private String content;
    /**
     * 封面的图片
     */	
	private String imageUrl;

    /**
     * 是否发布：1：未发布  0: 发布
     */
	private Integer isPublish;
    /**
     * 是否删除：1：删除，0：可用
     */	
	private Integer isDelete;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 创建人的id
     */	
	private Long createUserId;
    /**
     * createUserName
     */	
	private String createUserName;
    /**
     * 更新时间
     */	
	private Date updateTime;
    /**
     * 更新人id
     */	
	private Long updateUserId;
    /**
     * 更新人用户名
     */	
	private String updateUserName;
    /**
     * orderValue
     */	
	private Integer orderValue;
    /**
     * 阅读数量
     */	
	private Integer amount;

    /**
     * 是否横幅 0-是 1-不是
     */
    private Integer isBanner;

    //columns END

}


