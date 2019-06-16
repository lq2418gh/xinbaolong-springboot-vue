package com.bit.sc.module.restTemplate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bit.sc.common.Const;
import com.bit.sc.module.restTemplate.param.TokenRequest;
import com.bit.sc.module.restTemplate.dto.FaceCloudToken;
import com.bit.sc.module.restTemplate.vo.BaseFaceVo;
import com.bit.sc.utils.MD5;
import com.bit.utils.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: token维护服务
 * @Author: liyujun
 * @Date: 2018-11-14
 **/

@Component
public class FaceCloudTokenService {

    private static final Logger logger = LoggerFactory.getLogger(FaceCloudTokenService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheUtil cacheUtil;

    @Value("${facestar.loginName}")
    private String loginName;
    @Value("${facestar.password}")
    private String password;
    @Value("${facestar.address}")
    private String address;


    /**
     * @description: 维持token过期
     * @author liyujun
     * @date 2018-11-14
     * @return : void// fixedDelay
     * 半小时请求一次
     */
    @Scheduled(fixedRate=1800*1000)
    private void creatOrRefreshToken() {
          HttpHeaders headers =null;
          HttpStatus statusCode =null;
          Object o=null;
          String url=null;
        if (cacheUtil.get((Const.REDIS_KEY_FACE_CLOUD_TOKEN)) != null) {
            long time = cacheUtil.getExpire(Const.REDIS_KEY_FACE_CLOUD_TOKEN);
            //token小于半小时刷新
            if (time <= 1800){
                /*有token，进行刷新*/
                Map<String, String> map = new HashMap<>();
                FaceCloudToken oldToken = JSONObject.parseObject((String)cacheUtil.get(Const.REDIS_KEY_FACE_CLOUD_TOKEN), FaceCloudToken.class);
                map.put("refreshToken", oldToken.getRefreshToken());
                o=map;
                url = address + "/api/v2/auth/refresh";
            }else {
                return;
            }
        } else {
            TokenRequest tokenRequest = new TokenRequest();
            tokenRequest.setLoginName(loginName);
            tokenRequest.setPassword(MD5.encodeMd5(password));
             o=tokenRequest;
             url = address + "/api/v2/auth/login";
        }
        try {
            ResponseEntity<BaseFaceVo> responseEntity = restTemplate.postForEntity(url, o, BaseFaceVo.class);
            logger.error("请求的参数为{}"+JSONObject.toJSONString(o));
            headers = responseEntity.getHeaders();
            statusCode = responseEntity.getStatusCode();
            if (statusCode.value() == HttpStatus.OK.value()) {
                FaceCloudToken faceCloudToken = new FaceCloudToken();
                faceCloudToken.setAccessToken(headers.get("accessToken").get(0));
                faceCloudToken.setAccessTokenExp(headers.get("accessTokenExp").get(0));
                faceCloudToken.setRefreshToken(headers.get("refreshToken").get(0));
                faceCloudToken.setRefreshTokenExp(headers.get("refreshTokenExp").get(0));
                cacheUtil.set(Const.REDIS_KEY_FACE_CLOUD_TOKEN, JSONObject.toJSONString(faceCloudToken), Long.valueOf(headers.get("accessTokenExp").get(0)));
            } else {
                logger.error("token失败");
                return;
            }
        }catch (Exception e){
            logger.error("刷新token失败,message{}"+e.getMessage());
        }

    }

  }
