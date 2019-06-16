package com.bit.sc.module.attachment.vo;

import lombok.Data;

/**
 * 调用第三方上传文件时使用的bean
 * @author liuyancheng
 * @create 2018-12-14 15:08
 */
@Data
public class AttachmentThirdVO {
    /**
     * 附件表id
     */
    private Long attachmentId;
    /**
     * 第三方返回的fileId
     */
    private String fileId;
}
