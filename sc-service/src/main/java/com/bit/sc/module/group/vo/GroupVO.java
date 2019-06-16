package com.bit.sc.module.group.vo;

import java.util.Date;
import java.util.List;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * Group
 * @author generator
 */
@Data
public class GroupVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 组名
     */	
	private String name;
    /**
     * 地址code
     */	
	private String addressCode;
    /**
     * 第三方id
     */	
	private String groupCode;
    /**
     * 每日启用时间（格式：HH:mm:ss ）
     */	
	private String dayBegintime;
    /**
     * 每日结束时间（格式：HH:mm:ss ）
     */	
	private String dayEndtime;
    /**
     * 0：星期一； 1：星期二； 。。 6：星期日 eg："0,1,2,3,4,5,6"(每天)  "1,3,5" (周二，周四，周六) 重复指定的场合，distinct处理
     */	
	private String weekdays;
    /**
     * 备注
     */	
	private String remarks;
    /**
     * 分组类型 1-居民分组 2-车辆分组
     */	
	private Integer groupType;
    /**
     * 分组状态 1-启用 0-停用
     */	
	private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    private List<Long> ids;

    private List<String> groupCodes;
	//columns END

}


