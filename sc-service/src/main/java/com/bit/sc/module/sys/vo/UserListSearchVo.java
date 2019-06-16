package com.bit.sc.module.sys.vo;

import com.bit.base.vo.BasePageVo;
import com.bit.base.vo.BaseVo;
import lombok.Data;

import java.util.Date;

@Data
public class UserListSearchVo extends BasePageVo {

    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 起始时间
     */
    private String insertTimeStart;
    /**
     * 结束时间
     */
    private String insertTimeEnd;


}
