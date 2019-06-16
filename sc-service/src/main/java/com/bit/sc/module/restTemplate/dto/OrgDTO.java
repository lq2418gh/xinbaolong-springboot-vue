package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-26 19:34
 */
@Data
public class OrgDTO extends ResultDTO {

    /**
     * 组织机构id
     */
    private String orgId;
    /**
     * 组织机构名/公司名
     */
    private String orgName;
    /**
     * 所在地址
     */
    private String address;
    /**
     * 联系人
     */
    private String orgEmail;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 公司logo图片
     */
    private String logo;
    /**
     * 消息服务器地址
     */
    private String mqServer;
    /**
     * 消息服务器用户名
     */
    private String mqUserName;
    /**
     * 消息服务器密码
     */
    private String mqPassword;
    /**
     * 消息服务器虚拟机
     */
    private String mqVhost;
    /**
     * 允许访问的第三方服务器IP
     */
    private List ipCheckOn;
    /**
     * 有效期开始日期
     */
    private String validDateBegin;
    /**
     * 有效期结束日期
     */
    private String validDateEnd;
    /**
     * 秘钥
     */
    private String authKey;
}
