package com.bit.sc.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import com.bit.base.exception.BusinessException;
import com.bit.sc.module.user.pojo.User;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuyancheng
 * @create 2018-11-13 11:42
 */
public class JPushUtil {

    protected static final Logger LOG = LoggerFactory.getLogger(JPushUtil.class);

    private static JPushClient jPushClient;
    private static final long Longest_time_toLive=10*24*60*60;	//10天
    private static final int ONLY_CONTENT_LENGTH=115;		//单独发通知的字节长度
    private static final int CONTENT_EXTRA_LENGTH=200;		//通知内容加参数最长字节长度
    private static final boolean isProduct=false;			//是否是产品模式

    static{
        ConcurrentHashMap<String,String> yml = YmlUtil.getYmlByFileName("bootstrap.yml");
        String appKey = yml.get("jpush.appkey");
        String masterSecret = yml.get("jpush.masterSecret");
        jPushClient=new JPushClient(masterSecret, appKey);
        System.out.println(appKey);
        System.out.println(masterSecret);
    }

    public static void main(String[] args) {
        String[] regIDs = new String[]{"0900e8d85ef"};
//        JPushUtil.pushNotification("测试","测试内容",regIDs,"null",null,24*60*60);
        PushResult pushResult = JPushUtil.pushBroadCast("测试是否发送");
        System.out.println(pushResult);
    }

    /**
     * @Title: pushBroadCast
     * @Description: 给所有人发送广播
     * @param @param content	推送内容
     * @param @throws BusinessException
     * @return void
     * @throws
     */
    public static PushResult pushBroadCast(String content) throws BusinessException{
        PushResult pushResult = null;
        try {
            pushResult = jPushClient.sendNotificationAll(content);
            LOG.info("发送广播消息"+content+"成功");
            if(!pushResult.isResultOK()){
                String msg="发送广播失败!发送的原始内容是："+pushResult.getOriginalContent();
                LOG.info(msg);
                throw new BusinessException(msg);
            }
        } catch (APIConnectionException e) {
            String msg="发送广播失败!失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        } catch (APIRequestException e) {
            String msg="发送广播失败!失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        return pushResult;
    }

    /**
     * @Title: pushNotificationByRegIds
     * @Description: 把通知推送给指定的移动设备
     * @param @param title  标题
     * @param @param content，如果只有消息内容的话，content内容不能超过115个字节，一个汉字点两个字节长度
     * @param @param regIDList	设备的唯一注册号
     * @param @return
     * @param @throws BusinessException
     * @return PushResult
     * @throws
     */
    public static PushResult pushNotificationByRegIds(String title,String content,List<String> regIDList) throws BusinessException{
        PushResult pushResult =null;
        if(regIDList==null || regIDList.size()<=0){
            String msg="接收用户不能为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(content==null || "".equals(content)){
            String msg="推送消息失败，失败的原因是：推送的内容为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(getContentLength(content)>ONLY_CONTENT_LENGTH){
            String msg="推送消息的内容长度不能超过"+ONLY_CONTENT_LENGTH+"个字节，1个汉字占两个字节";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        List<String[]> subList = sub1000StringList(regIDList);
        for (int i = 0; i < subList.size(); i++) {
            String[] regIDs=subList.get(i);
            pushResult =pushNotification(title,content,regIDs,null,null,0);
        }
        return pushResult;
    }

    /**
     * 通过tag标签进行推送（所有平台）
     * @param title     标题
     * @param content   内容
     * @param tags      标签（tag）
     * @param extras    扩展的业务字段，以key,value形式存在，比如IOS设置通知铃声和badge
     * @return
     * @throws BusinessException
     */
    public static PushResult pushNotificationByTags(String title,String content,List<String> tags,ImmutableMap<String, String> extras) throws BusinessException{
        PushResult pushResult =null;
        if(content==null || "".equals(content)){
            String msg="推送消息失败，失败的原因是：推送的内容为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        int mapLength = getImmutableMapLength(extras);
        int contentLength=getContentLength(content);
        if((mapLength+contentLength)>CONTENT_EXTRA_LENGTH){
            String msg="推送消息的内容和扩展参数长度不能超过"+CONTENT_EXTRA_LENGTH+"个字节，1个汉字占两个字节，你推送的内容加参数长度为"+(mapLength+contentLength)+"字节";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        pushResult =pushNotificationByTags(title, content, tags, null, extras, 60*60*60,isProduct);
        return pushResult;
    }


    /**
     * 通过别名发送通知（所有平台）
     * @param title     标题
     * @param content   内容
     * @param aliases   别名
     * @param extras    扩展的业务字段，以key,value形式存在，比如IOS设置通知铃声和badge
     * @return
     * @throws BusinessException
     */
    public static int pushNotificationByAliases(String title,String content,List<String> aliases,ImmutableMap<String, String> extras) throws BusinessException{
        int result = 0;
        PushResult pushResult =null;
        if(content==null || "".equals(content)){
            String msg="推送消息失败，失败的原因是：推送的内容为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        int mapLength = getImmutableMapLength(extras);
        int contentLength=getContentLength(content);
        if((mapLength+contentLength)>CONTENT_EXTRA_LENGTH){
            String msg="推送消息的内容和扩展参数长度不能超过"+CONTENT_EXTRA_LENGTH+"个字节，1个汉字占两个字节，你推送的内容加参数长度为"+(mapLength+contentLength)+"字节";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        pushResult =pushNotificationByAlias(title, content, aliases, null, extras, 60*60*60,isProduct);
        if(pushResult.isResultOK()){
            result = 1;
        }
        return result;
    }


    /**
     * @Title: pushNotificationByRegIds
     * @Description: 把通知推送给指定的移动设备
     * @param title     标题
     * @param content      内容
     * @param regIDList	设备的唯一注册号
     * @param extras	扩展的业务字段，以key,value形式存在
     * @param
     * @param @throws BusinessException
     * @return PushResult
     * @throws
     */
    public static PushResult pushNotificationByRegIds(String title,String content,List<String> regIDList,ImmutableMap<String, String> extras) throws BusinessException{
        PushResult pushResult =null;
        if(regIDList==null || regIDList.size()<=0){
            String msg="接收用户不能为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(content==null || "".equals(content)){
            String msg="推送消息失败，失败的原因是：推送的内容为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        int mapLength = getImmutableMapLength(extras);
        int contentLength=getContentLength(content);
        if((mapLength+contentLength)>CONTENT_EXTRA_LENGTH){
            String msg="推送消息的内容和扩展参数长度不能超过"+CONTENT_EXTRA_LENGTH+"个字节，1个汉字占两个字节，你推送的内容加参数长度为"+(mapLength+contentLength)+"字节";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        List<String[]> subList = sub1000StringList(regIDList);
        for (int i = 0; i < subList.size(); i++) {
            String[] regIDs=subList.get(i);
            pushResult =pushNotification(title,content,regIDs,null,extras,0);
        }
        return pushResult;
    }

    /**
     * @Title: pushNotificationByRegIds
     * @Description: 把通知推送给指定的移动设备（IOS）
     * @param title
     * @param content
     * @param regIDList	设备的唯一注册号
     * @param iosSoundName	如果ios客户端有特殊的通知声音，此处可以设置该声音的名称
     * @param extras	扩展的业务字段，以key,value形式存在
     * @param
     * @param @throws BusinessException
     * @return PushResult
     * @throws
     */
    public static PushResult pushNotificationByRegIds(String title,String content,List<String> regIDList,String iosSoundName,ImmutableMap<String, String> extras) throws BusinessException{
        PushResult pushResult =null;
        if(regIDList==null || regIDList.size()<=0){
            String msg="接收用户不能为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(content==null || "".equals(content)){
            String msg="推送消息失败，失败的原因是：推送的内容为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        int mapLength = getImmutableMapLength(extras);
        int contentLength=getContentLength(content);
        if((mapLength+contentLength)>CONTENT_EXTRA_LENGTH){
            String msg="推送消息的内容和扩展参数长度不能超过"+CONTENT_EXTRA_LENGTH+"个字节，1个汉字占两个字节，你推送的内容加参数长度为"+(mapLength+contentLength)+"字节";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        List<String[]> subList = sub1000StringList(regIDList);
        for (int i = 0; i < subList.size(); i++) {
            String[] regIDs=subList.get(i);
            pushResult =pushNotification(title,content,regIDs,iosSoundName,extras,0);
        }
        return pushResult;
    }

    /**
     * @Title: pushNotificationByRegIds
     * @Description: 把通知推送给指定的移动设备（可设置消息保存多长时间）
     * @param title
     * @param content
     * @param regIDList	设备的唯一注册号
     * @param iosSoundName	如果ios客户端有特殊的通知声音，此处可以设置该声音的名称,可以为空
     * @param extras	扩展的业务字段，以key,value形式存在
     * @param timeToLive	在服务器上离线保存消息多长时间，秒为最小单位
     * @param
     * @param @throws BusinessException
     * @return PushResult
     * @throws
     */
    public static PushResult pushNotificationByRegIds(String title,String content,List<String> regIDList,String iosSoundName,ImmutableMap<String, String> extras,long timeToLive) throws BusinessException{
        PushResult pushResult =null;
        if(regIDList==null || regIDList.size()<=0){
            String msg="接收用户不能为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(content==null || "".equals(content)){
            String msg="推送消息失败，失败的原因是：推送的内容为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        int mapLength = getImmutableMapLength(extras);
        int contentLength=getContentLength(content);
        if((mapLength+contentLength)>CONTENT_EXTRA_LENGTH){
            String msg="推送消息的内容和扩展参数长度不能超过"+CONTENT_EXTRA_LENGTH+"个字节，1个汉字占两个字节，你推送的内容加参数长度为"+(mapLength+contentLength)+"字节";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        List<String[]> subList = sub1000StringList(regIDList);
        for (int i = 0; i < subList.size(); i++) {
            String[] regIDs=subList.get(i);
            pushResult =pushNotification(title,content,regIDs,iosSoundName,extras,timeToLive);
        }
        return pushResult;
    }

    /**
     * @Title: pushNotificationByRegIds
     * @Description: 定时推送通知通知推送给指定的移动设备
     * @param title
     * @param content
     * @param implementDate	预设的推送时间
     * @param regIDList	设备的唯一注册号
     * @param iosSoundName	如果ios客户端有特殊的通知声音，此处可以设置该声音的名称,可以为空
     * @param extras	扩展的业务字段，以key,value形式存在
     * @param timeToLive	在服务器上离线保存消息多长时间，秒为最小单位
     * @param
     * @param @throws BusinessException
     * @return PushResult
     * @throws
     */
    @SuppressWarnings("deprecation")
    public static PushResult pushNotificationByRegIdsAndTimer(final Date implementDate,final String title,final String content,List<String> regIDList,final String iosSoundName,final ImmutableMap<String, String> extras,final long timeToLive) throws BusinessException{
        PushResult pushResult =null;
        Date now=new Date();
        if(implementDate==null){
            String msg="预设的推送时间为空，不能执行定时发送";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(now.after(implementDate)){
            String msg="预设的发送时间 "+ implementDate.toLocaleString() +" 早于当前时间"+ now.toLocaleString() +"，不能执行定时发送";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(regIDList==null || regIDList.size()<=0){
            String msg="接收用户不能为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        if(content==null || "".equals(content)){
            String msg="推送消息失败，失败的原因是：推送的内容为空";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        int mapLength = getImmutableMapLength(extras);
        int contentLength=getContentLength(content);
        if((mapLength+contentLength)>CONTENT_EXTRA_LENGTH){
            String msg="推送消息的内容和扩展参数长度不能超过"+CONTENT_EXTRA_LENGTH+"个字节，1个汉字占两个字节，你推送的内容加参数长度为"+(mapLength+contentLength)+"字节";
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        List<String[]> subList = sub1000StringList(regIDList);
        for (int i = 0; i < subList.size(); i++) {
            final String[] regIDs=subList.get(i);
            final Timer pushTimer=new Timer();
            pushTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        pushNotification(title,content,regIDs,iosSoundName,extras,timeToLive);
                        LOG.info("定时("+implementDate.toLocaleString()+")发送通知成功");
                    } catch (BusinessException e) {
                        String msg="定时("+implementDate.toLocaleString()+")发送通知失败，失败的原因是："+e.getMessage();
                        LOG.error(msg);
                    }
                    pushTimer.cancel();
                }

            },implementDate);

        }
        return pushResult;
    }

    /**
     * @Title: pushNotification
     * @Description: 推送一条通知
     * @param title:通知的标题，android使用，不能为空
     * @param content：通知的内容，不能为空
     * @param regIDs:接收的设备注册ID数组,不能为空
     * @param soundName:IOS消息的声音名称，可以为空，为空表示调用系统默认声音
     * @param extras:业务数据，以key,valus形式存在，可以为空
     * @param timeLive:离线消息保存最长时间，0表示不保存到服务器中，最长保留10天时间，1天则填写24*60*60
     * @param @throws BusinessException
     * @return PushResult
     * @throws
     */
    private static PushResult pushNotification(String title,String content,String[] regIDs,String soundName,ImmutableMap<String, String> extras,long timeLive) throws BusinessException{
        PushResult pushResult =null;
        Platform platform=Platform.all();			//设置要推送的平台,all表示，android,ios,windowPhone都推送
        String _tempReceiver="";
        for (int i = 0; i < regIDs.length; i++) {
            _tempReceiver+=regIDs[i];
            if(i<regIDs.length-1) _tempReceiver+=",";
        }
        LOG.info("接收通知的用户为："+_tempReceiver);
        Audience audience=Audience.registrationId(regIDs);	//设置接收通知的用户设备,即那些手机要收到该条通知
        AndroidNotification.Builder androidBuilder=AndroidNotification.newBuilder();
        androidBuilder.setAlert(content).setTitle(title);
        //ios通知设置
        IosNotification.Builder iosBuild=IosNotification.newBuilder();
        String iosSound =(soundName!=null&& "".equals(soundName))?soundName:"default";	//ios声音设置，如果为空，设置为默认声音
        iosBuild.setAlert(content).setSound(iosSound).setBadge(0);						//badge为ios消息角标
        //扩展字段可以为空,业务数据
        if(extras!=null){
            Iterator<String> it = extras.keySet().iterator();
            while(it.hasNext()){
                String key = it.next();
                androidBuilder.addExtra(key, extras.get(key));
                iosBuild.addExtra(key, extras.get(key));
            }
        }
        PlatformNotification androidPNotification=androidBuilder.build();
        PlatformNotification iosPNotification=iosBuild.build();
        Notification notification=Notification.newBuilder().addPlatformNotification(androidPNotification).addPlatformNotification(iosPNotification).build();
        Builder pushPayloadBuild=PushPayload.newBuilder().setAudience(audience).setPlatform(platform).setNotification(notification);
        //timeLive=0表示不保留消息到服务器上。
        if(timeLive!=0 && timeLive<Longest_time_toLive){
            Options options=Options.newBuilder().setTimeToLive(timeLive).build();
            pushPayloadBuild.setOptions(options);
        }else if(timeLive!=0 && timeLive>Longest_time_toLive){
            //如果超过10天，就设置成10天
            Options options=Options.newBuilder().setTimeToLive(Longest_time_toLive).build();
            pushPayloadBuild.setOptions(options);
        }
        PushPayload pushPayload =pushPayloadBuild.build();
        try {
            pushResult = jPushClient.sendPush(pushPayload);
            LOG.info("推送消息成功：消息ID为"+pushResult.msg_id);
        } catch (APIConnectionException e) {
            String msg="推送消息失败，失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        } catch (APIRequestException e) {
            String msg="推送消息失败，失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        return pushResult;
    }


    /**
     * 通过 tag进行发送
     * @param title
     * @param content
     * @param tags
     * @param soundName
     * @param extras
     * @param timeLive
     * @return
     * @throws BusinessException
     */
    private static PushResult pushNotificationByTags(String title,String content,List<String> tags ,String soundName,ImmutableMap<String, String> extras,long timeLive,boolean isProduction) throws BusinessException{
        PushResult pushResult =null;
        //设置要推送的平台,all表示，android,ios,windowPhone都推送
        Platform platform=Platform.all();
        String _tempReceiver="";
        if(timeLive==0l){
            timeLive=60*60;
        }
        LOG.info("接收通知的用户为："+_tempReceiver);
        Audience audience=Audience.tag(tags);	//通过tag进行发送
        AndroidNotification.Builder androidBuilder=AndroidNotification.newBuilder();
        androidBuilder.setAlert(content).setTitle(title);
        //ios通知设置
        IosNotification.Builder iosBuild=IosNotification.newBuilder();
        String iosSound =(soundName!=null&& "".equals(soundName))?soundName:"default";	//ios声音设置，如果为空，设置为默认声音
        iosBuild.setAlert(content).setSound(iosSound).setBadge(0);						//badge为ios消息角标
        //扩展字段可以为空,业务数据
        if(extras!=null){
            Iterator<String> it = extras.keySet().iterator();
            while(it.hasNext()){
                String key = it.next();
                androidBuilder.addExtra(key, extras.get(key));
                iosBuild.addExtra(key, extras.get(key));
            }
        }
        PlatformNotification androidPNotification=androidBuilder.build();
        PlatformNotification iosPNotification=iosBuild.build();
        Notification notification=Notification.newBuilder().addPlatformNotification(androidPNotification).addPlatformNotification(iosPNotification).build();
        Builder pushPayloadBuild=PushPayload.newBuilder().setAudience(audience).setPlatform(platform).setNotification(notification);
        //timeLive=0表示不保留消息到服务器上。
        if(timeLive!=0 && timeLive<Longest_time_toLive){
            //setApnsProduction	true表示发生产环境，false表示测试环境
            Options options=Options.newBuilder().setTimeToLive(timeLive).setApnsProduction(isProduction).build();
            pushPayloadBuild.setOptions(options);
        }else if(timeLive!=0 && timeLive>Longest_time_toLive){
            //如果超过10天，就设置成10天
            Options options=Options.newBuilder().setTimeToLive(Longest_time_toLive).setApnsProduction(isProduction).build();
            pushPayloadBuild.setOptions(options);
        }
        PushPayload pushPayload =pushPayloadBuild.build();
        try {
            pushResult = jPushClient.sendPush(pushPayload);
            LOG.info("推送消息成功：消息ID为"+pushResult.msg_id);
        } catch (APIConnectionException e) {
            String msg="推送消息失败，失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        } catch (APIRequestException e) {
            String msg="推送消息失败，失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        return pushResult;
    }


    /**
     * 通过 aliases进行发送
     * @param title
     * @param content
     * @param soundName
     * @param extras
     * @param timeLive
     * @return
     * @throws BusinessException
     */
    private static PushResult pushNotificationByAlias(String title,String content,List<String> aliases ,String soundName,ImmutableMap<String, String> extras,long timeLive,boolean isProduction) throws BusinessException{
        PushResult pushResult =null;
        Platform platform=Platform.all();			//设置要推送的平台,all表示，android,ios,windowPhone都推送
        String _tempReceiver="";
        if(timeLive==0l){
            timeLive=60*60;
        }
        LOG.info("接收通知的用户为："+_tempReceiver);
        Audience audience=Audience.alias(aliases);	//通过别名进行发送
        AndroidNotification.Builder androidBuilder=AndroidNotification.newBuilder();
        androidBuilder.setAlert(content).setTitle(title);
        //ios通知设置
        IosNotification.Builder iosBuild=IosNotification.newBuilder();
        String iosSound =(soundName!=null&& "".equals(soundName))?soundName:"default";	//ios声音设置，如果为空，设置为默认声音
        iosBuild.setAlert(content).setSound(iosSound).setBadge(0);						//badge为ios消息角标
        //扩展字段可以为空,业务数据
        if(extras!=null){
            Iterator<String> it = extras.keySet().iterator();
            while(it.hasNext()){
                String key = it.next();
                androidBuilder.addExtra(key, extras.get(key));
                iosBuild.addExtra(key, extras.get(key));
            }
        }
        PlatformNotification androidPNotification=androidBuilder.build();
        PlatformNotification iosPNotification=iosBuild.build();
        Notification notification=Notification.newBuilder().addPlatformNotification(androidPNotification).addPlatformNotification(iosPNotification).build();
        Builder pushPayloadBuild=PushPayload.newBuilder().setAudience(audience).setPlatform(platform).setNotification(notification);
        //timeLive=0表示不保留消息到服务器上。
        if(timeLive!=0 && timeLive<Longest_time_toLive){
            //setApnsProduction	true表示发生产环境，false表示测试环境
            Options options=Options.newBuilder().setTimeToLive(timeLive).setApnsProduction(isProduction).build();
            pushPayloadBuild.setOptions(options);
        }else if(timeLive!=0 && timeLive>Longest_time_toLive){
            //如果超过10天，就设置成10天
            Options options=Options.newBuilder().setTimeToLive(Longest_time_toLive).setApnsProduction(isProduction).build();
            pushPayloadBuild.setOptions(options);
        }
        PushPayload pushPayload =pushPayloadBuild.build();
        try {
            pushResult = jPushClient.sendPush(pushPayload);
            LOG.info("推送消息成功：消息ID为"+pushResult.msg_id);
        } catch (APIConnectionException e) {
            String msg="推送消息失败，失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        } catch (APIRequestException e) {
            String msg="推送消息失败，失败的原因是："+e.getMessage();
            LOG.error(msg);
            throw new BusinessException(msg);
        }
        return pushResult;
    }
    /**
     *
     * @Title: sub1000StringList
     * @Description: 极光推送一次只能推送最多1000个设备，将需要发送的设备列表按1000个进行拆分。
     * @param list 要拆分的List<String>
     * @return List<String[]>
     * @throws
     */
    private static List<String[]> sub1000StringList(List<String> list){
        List<String[]> result=new ArrayList<String[]>();
        if(list==null || list.size()==0)return result;
        if(list.size()<=1000){
            String[] resultAll=new String[list.size()];
            for (int i = 0; i < resultAll.length; i++) {
                resultAll[i]=list.get(i);
            }
            result.add(resultAll);
        }else{
            int regLength=list.size();		//总共要发的条数
            int batchCount=(regLength%1000+1);	//需要分多少批进行发送
            for (int i = 0; i < batchCount; i++) {
                int start=i*1000;
                int end=start+999;
                if(i==batchCount-1){
                    end=regLength;
                }
                List<String> subResultList = list.subList(start, end);
                String[] resultAll=new String[subResultList.size()];
                for (int j = 0; j < resultAll.length; j++) {
                    resultAll[j]=subResultList.get(j);
                }
                result.add(resultAll);
            }
        }
        return result;
    }

    /**
     * @Title: getContentLength
     * @Description: 计算字符串所占长度，1个汉字的长度为2，其他字符为1，如"123abc长城",长度为10
     * @param @param content
     * @param @return
     * @return int
     * @throws
     */
    private static int getContentLength(String content){
        int result=0;
        content = content.replaceAll("[^\\x00-\\xff]", "**");
        result= content.length();
        return result;
    }

    /**
     *
     * @Title: getImmutableMapLength
     * @Description: 计算map的key和value所占字节数，1个中文占两个字节,如果map为空，直接返回0
     * @param @param map
     * @param @return
     * @return int
     * @throws
     */
    private static int getImmutableMapLength(ImmutableMap<String,String> map){
        int result=0;
        if(map==null) return result;
        ImmutableSet<String> keySet =map.keySet();
        UnmodifiableIterator<String> ufIter = keySet.iterator();
        while(ufIter.hasNext()){
            String key = ufIter.next();
            String value = map.get(key).toString();
            result+=getContentLength(key)+getContentLength(value);
        }
        return result;
    }

}
