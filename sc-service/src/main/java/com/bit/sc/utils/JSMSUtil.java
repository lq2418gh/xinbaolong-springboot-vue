package com.bit.sc.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import com.bit.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class JSMSUtil {

    protected static final Logger LOG = LoggerFactory.getLogger(JSMSUtil.class);

    //发送验证码
    public static String sendSMSCode(String phone) {//
        ConcurrentHashMap<String, String> yml = YmlUtil.getYmlByFileName("bootstrap.yml");
        String appkey = yml.get("jsms.appkey");
        String masterSecret = yml.get("jsms.masterSecret");
        //String phone = yml.get("jsms.phone");
        String temp_id = yml.get("jsms.temp_id");
        SMSClient client = new SMSClient(masterSecret, appkey);
        SMSPayload payload = SMSPayload.newBuilder()
                //设置验证手机号
                .setMobildNumber(phone)
                //获取模板信息
                .setTempId(Integer.parseInt(temp_id))
                .build();

        String msg_id ;//
        try {
            SendSMSResult res = client.sendSMSCode(payload);
            msg_id = res.getMessageId();//
            LOG.info("发送短信验证码：" + res.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            throw new BusinessException("验证码服务出错");
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            throw new BusinessException("验证码服务出错");
        }
        return msg_id;//
    }

    /**
     * 验证码校验
     * @param msg_id
     * @param code
     * @return
     */
    public static boolean testSendValidSMSCode(String msg_id, String code) {
        ConcurrentHashMap<String, String> yml = YmlUtil.getYmlByFileName("bootstrap.yml");
        String masterSecret = yml.get("jsms.masterSecret");
        String appkey = yml.get("jsms.appkey");
        SMSClient client = new SMSClient(masterSecret, appkey);
        boolean valid = false;
        try {
            ValidSMSResult res = client.sendValidSMSCode(msg_id, code);
            valid = res.getIsValid();
            LOG.info(res.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            throw new BusinessException("验证码服务出错");
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
            LOG.info("Error ErrorCode: " + e.getErrorCode());
            if (e.getErrorCode() == 50011) {
                throw new BusinessException("验证码过期");
            }else if (e.getErrorCode() == 50010) {
                throw new BusinessException("验证码无效");
            }else {
                throw new BusinessException("验证码服务出错");
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        //JSMSUtil.sendSMSCode("18222845248");
        JSMSUtil.testSendValidSMSCode("632307914587", "514969");
    }
}
