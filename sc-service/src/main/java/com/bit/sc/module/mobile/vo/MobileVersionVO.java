package com.bit.sc.module.mobile.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * MobileVersion
 * @author generator
 */
@Data
public class MobileVersionVO extends BasePageVo{

	//columns START

    /**
     * 主键
     */	
	private String id;
    /**
     * 版本号
     */	
	private String version;
    /**
     * 文件名称
     */	
	private String fileName;
    /**
     * 上传用户ID
     */	
	private Long userId;
    /**
     * 上传用户姓名
     */	
	private String userName;
    /**
     * 上传时间
     */	
	private Date uploadTime;
    /**
     * 下载次数
     */	
	private Long downloadCount;
    /**
     * 状态 1 启用  0 停用
     */	
	private Integer status;
    /**
     * 手机类型  1 Android手机  2 IOS手机  3 Android PAD  4 IPAD
     */	
	private Integer phoneType;
    /**
     * 文件类型  1 apk   2 ios
     */	
	private Integer fileType;
    /**
     * 业务类型  1 居民APP  2 管理APP  3 信息录入
     */
    private Integer moduleType;
	//columns END

}


