package com.bit.sc.module.restTemplate.service;

import com.bit.sc.module.restTemplate.dto.FileDTO;
import com.bit.sc.module.restTemplate.vo.ResultDTO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author liuyancheng
 * @create 2018-11-28 17:05
 */
public interface RestTemplateService {

    /**
     * 发送get请求
     * @param url   请求地址
     * @return
     */
    ResultDTO sendGetRequest(String url);

    /**
     * 发送post请求
     * @param url   请求地址
     * @param map   请求参数对象
     * @return
     */
//    ResultDTO sendPostRequest(String url, Map<String,Object> map);
    <T> T sendPostRequest(String url, Map<String,Object> map,Class<T> tClass);

    /**
     * 上传文件post
     * @param url
     * @return
     */
    FileDTO sendPostFileRequest(String url,MultipartFile multipartFile);



    /**
     * 上传文件post
     * @param url
     * @return
     */
    FileDTO sendPostFileRequestNew(String url,MultipartFile multipartFile,Map<String,Object> headersParams);
}
