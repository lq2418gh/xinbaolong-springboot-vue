package com.bit.sc.module.sys.vo;

import java.util.Date;
import java.util.List;

import com.bit.base.vo.BasePageVo;
import com.bit.sc.module.sys.pojo.Resource;
import lombok.Data;

/**
 * Resource
 * @author liqi
 */
@Data
public class ResourceVO extends BasePageVo{

	//columns START

    /**
     * 数据id
     */	
	private Long id;
    /**
     * 对应应用的id(t_sys_apply的id)
     */	
	private Long applicationId;
    /**
     * 资源名称
     */	
	private String resourceName;
    /**
     * 资源图标
     */	
	private String resourceIcon;
    /**
     * 资源等级无用
     */	
	private Integer resourceLevel;
    /**
     * 资源地址,根路径是空
     */	
	private String resourceUrl;
    /**
     * 资源编码
     */	
	private String resourceCode;
    /**
     * 资源排序值
     */	
	private Integer orderValue;
    /**
     * 是否显示，1：显示 0：不显示
     */	
	private Integer isDisplay;
    /**
     * 父id
     */	
	private Long pid;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 创建者id
     */	
	private Long createUserId;
    /**
     * 更新时间
     */	
	private Date updateTime;
    /**
     * 更新者id
     */	
	private Long updateUserId;
    /**
     * 0是菜单1是按钮
     */	
	private Integer type;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 创建人名字
     */
    private String createUserName;
    /**
     * 更新人名字
     */
    private String updateUserName;
    /**
     * list
     */
    private List<Resource> childResources;
	//columns END

}


