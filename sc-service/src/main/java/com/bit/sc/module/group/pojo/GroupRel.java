package com.bit.sc.module.group.pojo;

import java.util.Date;
import lombok.Data;

/**
 * GroupRel
 * @author generator
 */
@Data
public class GroupRel {

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 组id
     */	
	private Long groupId;
    /**
     * id
     */	
	private Long relId;
    /**
     * 种类:0人,1设备
     */	
	private Integer type;
    /**
     * 组名
     */
	private String groupName;
    /**
     * 接收居民和设备的名称
     */
	private String relName;
	//columns END

}


