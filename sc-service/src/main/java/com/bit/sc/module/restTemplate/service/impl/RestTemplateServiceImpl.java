package com.bit.sc.module.restTemplate.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bit.base.exception.BusinessException;
import com.bit.sc.common.Const;
import com.bit.sc.common.FaceCloudConstants;
import com.bit.sc.module.restTemplate.dto.*;
import com.bit.sc.module.restTemplate.service.RestTemplateService;
import com.bit.sc.module.restTemplate.vo.ResultDTO;
import com.bit.sc.utils.JsonConvertUtil;
import com.bit.utils.CacheUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuyancheng
 * @create 2018-11-28 17:05
 */
@Service("RestTemplateService")
public class RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CacheUtil cacheUtil;
    /**
     *
     */
    public static String AUTH_KEY = "authKey";
    /**
     * 设备秘钥
     */
    public static String SECRET_KEY = "secretKey";
    /**
     * 设备序列号
     */
    public static String DEVICE_CODE = "deviceCode";

    /**
     * 发送get请求
     * @param url   请求地址
     * @return
     */
    @Override
    public ResultDTO sendGetRequest(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity,ResultDTO.class).getBody();
    }

    /**
     * 发送post请求
     * @param url   请求地址
     * @param map   请求参数对象
     * @return
     */
    @Override
    public <T> T sendPostRequest(String url, Map<String,Object> map,Class<T> tClass) {
        //将入参，出参，请求时间，请求地址存入mongodb中
        Map<String,Object> mongodbMap = new HashMap<>();
        HttpStatus statusCode =null;
        String tJsonStr = null;
        String requestBody = JSONObject.toJSONString(map.get("obj"));
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        FaceCloudToken oldToken = JSONObject.parseObject((String)cacheUtil.get(Const.REDIS_KEY_FACE_CLOUD_TOKEN), FaceCloudToken.class);
        headers.add("accessToken", oldToken.getAccessToken());

//        headers.add("orgId",map.get("orgId").toString());
        if (map.get(AUTH_KEY) != null){
            headers.add(AUTH_KEY,map.get(AUTH_KEY).toString());
        }
        if (map.get(SECRET_KEY) != null){
            headers.add(SECRET_KEY,map.get(SECRET_KEY).toString());
        }
        if (map.get(DEVICE_CODE) != null){
            headers.add(DEVICE_CODE,map.get(DEVICE_CODE).toString());
        }
        if(map.get(FaceCloudConstants.ORGID)!=null){
            headers.add(FaceCloudConstants.ORGID,map.get(FaceCloudConstants.ORGID).toString());
        }
        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

        try {
            ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, httpEntity, tClass);
            //获取HttpStatus
            statusCode = responseEntity.getStatusCode();
            //获取内容
            T t = responseEntity.getBody();

            tJsonStr = JSON.toJSONString(t);

            //判断是否返回code为0
            if (statusCode.value() == HttpStatus.OK.value()){
                //处理返回的内容
                JSONObject jsonObject = JSON.parseObject(tJsonStr);
                String code = jsonObject.getString("code");
                if (code.equals("0")){
                    String data = jsonObject.getString("data");
                    if (data == null){
                        return null;
                    }else {
                        return JSON.parseObject(data,tClass);
                    }
                }else {
                    throw new BusinessException(tJsonStr);
                }
            }
        }catch (Exception e){
            mongodbMap.put("result",e.getMessage());
            if (e.getMessage().contains("code")){
                JSONObject jsonObject = JSON.parseObject(e.getMessage());
                throw new BusinessException(jsonObject.getString("message"));
            }else {
                throw new BusinessException(e.getMessage());
            }
        } finally {
            //1.入参
            mongodbMap.put("inParam",requestBody);
            //2.请求时间
            mongodbMap.put("requestTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            //3.请求地址
            mongodbMap.put("requestUrl",url);
            //4.判断返回值是否为空
            if (mongodbMap.get("result") == null){
                mongodbMap.put("result",tJsonStr);
            }
            //5.调用mongoTemplate存入数据
            mongoTemplate.insert(mongodbMap,"third_Interface_logs");
        }
        return null;
    }

    @Override
    public FileDTO sendPostFileRequest(String url,MultipartFile multipartFile) {
        //http返回状态
        HttpStatus statusCode =null;
        //将入参，出参，请求时间，请求地址存入mongodb中
        Map<String,Object> mongodbMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        FaceCloudToken oldToken = JSONObject.parseObject((String)cacheUtil.get(Const.REDIS_KEY_FACE_CLOUD_TOKEN), FaceCloudToken.class);
        headers.add("accessToken", oldToken.getAccessToken());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(file,multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileSystemResource fileSystemResource = new FileSystemResource(new File(multipartFile.getOriginalFilename()));
        form.add("updateFile", fileSystemResource);

        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        ResponseEntity<FileDTO> fileDTOResponseEntity = restTemplate.postForEntity(url, files, FileDTO.class);
        //获取HttpStatus
        statusCode = fileDTOResponseEntity.getStatusCode();
        FileDTO body = restTemplate.postForEntity(url, files, FileDTO.class).getBody();

        String tJsonStr = JSON.toJSONString(body);
        JSONObject jsonObject = JSON.parseObject(tJsonStr);
        String code = jsonObject.getString("code");
        try {
            //上传成功，删除临时文件
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //判断是否返回code为0
            if (statusCode.value() == HttpStatus.OK.value()){
                if (code.equals("0")){
                    String data = jsonObject.getString("data");
                    if (data.equals("")){
                        return null;
                    }else {
                        return JSON.parseObject(data,FileDTO.class);
                    }
                }
            }else {
                throw new BusinessException(tJsonStr);
            }
        }catch (Exception e){
            mongodbMap.put("result",e.getMessage());
            if (e.getMessage().contains("code")){
                JSONObject jsonObj = JSON.parseObject(e.getMessage());
                throw new BusinessException(jsonObj.getString("message"));
            }else {
                throw new BusinessException(e.getMessage());
            }
        }finally {
            //1.入参
            mongodbMap.put("inParam",multipartFile.getOriginalFilename());
            //2.请求时间
            mongodbMap.put("requestTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            //3.请求地址
            mongodbMap.put("requestUrl",url);
            //4.返回结果
            if (mongodbMap.get("result") == null){
                mongodbMap.put("result",tJsonStr);
            }
            //5.调用mongoTemplate存入数据
            mongoTemplate.insert(mongodbMap,"third_Interface_logs");
        }
        return null;
    }


    @Override
    public FileDTO sendPostFileRequestNew(String url,MultipartFile multipartFile,Map<String,Object> headersParams){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        FaceCloudToken oldToken = JSONObject.parseObject((String)cacheUtil.get(Const.REDIS_KEY_FACE_CLOUD_TOKEN), FaceCloudToken.class);
        headers.add("accessToken", oldToken.getAccessToken());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if(headersParams.get(FaceCloudConstants.ORGID)!=null){
            headers.add(FaceCloudConstants.ORGID,headersParams.get(FaceCloudConstants.ORGID).toString());
        }
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(file,multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileSystemResource fileSystemResource = new FileSystemResource(new File(multipartFile.getOriginalFilename()));
        form.add("updateFile", fileSystemResource);

        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        FileDTO body = restTemplate.postForEntity(url, files, FileDTO.class).getBody();
        String tJsonStr = JSON.toJSONString(body);
        JSONObject jsonObject = JSON.parseObject(tJsonStr);
        String code = jsonObject.getString("code");
        String message = jsonObject.getString("message");
        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //判断是否返回code为0
            if (code.equals("0")){
                String data = jsonObject.getString("data");
                if (data.equals("")){
                    return null;
                }else {
                    return JSON.parseObject(data,FileDTO.class);
                }
            }else {
                throw new BusinessException(message);
            }
        }catch (Exception e){
            throw new BusinessException(message);
        }finally {
            //将入参，出参，请求时间，请求地址存入mongodb中
            Map<String,Object> mongodbMap = new ConcurrentHashMap<>();
            //1.入参
            mongodbMap.put("inParam",multipartFile.getOriginalFilename());
            //2.请求时间
            mongodbMap.put("requestTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            //3.请求地址
            mongodbMap.put("requestUrl",url);
            //4.返回结果
            mongodbMap.put("result", tJsonStr);
            //5.调用mongoTemplate存入数据
            mongoTemplate.insert(mongodbMap,"third_Interface_logs");
        }
    }
}
