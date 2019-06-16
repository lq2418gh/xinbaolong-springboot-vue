package com.bit.sc.module.sys.pojo;

import java.util.Date;
import java.util.List;
import lombok.Data;
/**
 * Menu
 * @author generator
 */
@Data
public class Menu {
	//columns START
    /**
     * 数据id
     */	
	private Long id;
    /**
     * 应用的编码
     */
    private Long applicationId;
    /**
     * 菜单名称
     */	
	private String menuName;
    /**
     * 菜单图标
     */	
	private String menuIcon;
    /**
     * 菜单等级
     */	
	private Integer menuLevel;
    /**
     * 菜单地址
     */	
	private String menuUrl;
    /**
     * 功能编码
     */	
	private String menuCode;
    /**
     * 功能排序值
     */	
	private Integer orderValue;
    /**
     * 是否显示，1：显示 0：不显示
     */	
	private Integer isDisplay;
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
     * 父id
     */	
	private Long pid;
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
     * 0 菜单 1按钮
     */
    private Integer type;

    private List<Menu> childMenus;
	//columns END

}


