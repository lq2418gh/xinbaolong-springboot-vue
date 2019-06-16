package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

/**
 * @author liuyancheng
 * @create 2018-12-12 17:01
 */
@Data
public class AccessDTO extends ResultDTO {
    private String clientId;
    private String clientSecret;
    private String refreshToken;
}
