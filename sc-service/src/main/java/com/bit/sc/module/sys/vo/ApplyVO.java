package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * Apply
 * @author generator
 */
@Data
public class ApplyVO extends BasePageVo{

    //columns START

    /**
     * id
     */
    private Long id;
    /**
     * 应用的code
     */
    private String applicationCode;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用的类型： 1：web  2:app
     */
    private Integer applicationType;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * createUserId
     */
    private Long createUserId;
    /**
     * updateTime
     */
    private Date updateTime;
    /**
     * updateUserId
     */
    private Long updateUserId;
    /**
     * isDelete
     */
    private Integer isDelete;

    //columns END

}


