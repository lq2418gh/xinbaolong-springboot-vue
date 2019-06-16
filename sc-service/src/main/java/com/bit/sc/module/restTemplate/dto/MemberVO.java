package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-05 14:10
 */
@Data
public class MemberVO extends ResultDTO {
    /**
     * 压缩的注册照片文件ID
     */
    private String imageZipFileId;
    /**
     * 标签名
     */
    private List tagName;
    /**
     * 用户列表
     */
    private List memberIdList;
    /**
     * 分组ID
     */
    private String groupId;
    /**
     * 标签数组
     */
    private List tagIdList;
    /**
     * 操作flag   1.添加 2.删除
     */
    private Integer optionFlag;
    /**
     * 设备id
     */
    private String deviceId;
    /**
     *  组织ID
     */
    private String orgId;

}
