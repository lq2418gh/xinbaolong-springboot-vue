package com.bit.sc.common;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 静态变量
 * @Author: mifei
 * @Date: 2018-10-19
 **/
public class Const {

    /**
     * token 过期时间
     */
    public static final long TOKEN_EXPIRE_SECONDS = 3600;

    /**
     *  根节点的PID
     */
    public static final long ROOT_MENU_PID = 100L;

    /**
     *  根节点的FID
     */
    public static final long ROOT_ADDRESS_FID = 0L;
    /**
     *  根节点的FID
     */
    public static final int ROOT_ADDRESS_LEVEL = 1;
    /**
     *  居民状态 默认为1
     */
    public static final int VILLAGE_STATE = 1;
    /**
     *  已经删除
     */
    public static final int DELETE = 1;

    /**
     * 没删除
     */
    public static final int NOT_DELETE = 0;
    /**
     * 是否显示 1显示 0不显示
     */
    public static final int IS_DISPLAY_Y = 1;
    public static final int IS_DISPLAY_N = 0;
    /**
     * 字典redis key
     */
    public static final String REDIS_KEY_DICT = "dict:";

    /**
     * 字典redis key
     */
    public static final String REDIS_KEY_CAPTCHA = "captcha:";

    /**
     * 字典redis key 短信验证码
     */
    public static final String REDIS_KEY_SMSCAPTCHA = "smsCaptcha:";
    /**
     * 字典redis key 短信验证码 发送频率
     */
    public static final String REDIS_KEY_SMSCAPTCHA_RATE = "smsCaptchaRate:";
    /**
     * redis key 小区
     */
    public static final String REDIS_KEY_ADDRESS = "address:";

    /**
     * 上传路径
     */
    public static final String UPLOAD_PATH = "/upload/";

    /**
     * 居民手机表 状态 未激活
     */
    public static Integer RESIDENT_STATUS_BINDED_NOT = 0;



    //居民手机表 状态
    public static Integer RESIDENT_STATUS_BINDED = 1;

    //时间转化格式
    public static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    /**
     *  未发布
     */
    public static final int NOT_PUBLISH = 1;

    /**
     * 已发布
     */
    public static final int PUBLISH = 0;

    /**
     *  不是横幅
     */
    public static final int NOT_BANNER = 1;

    /**
     * 是横幅
     */
    public static final int BANNER = 0;
    /**
     * 初始点击量
     */
    public static final int INITIAL_AMOUNT = 1;

    /**
     * 判断数据是否只有一条
     */
    public static final int ONLY_ONE = 1;

    /**
     * 居民登录token头
     */
    public static final String TOKEN_TITLE = "resident_App:";

    /**
     * 居民登录token头-暂时
     */
    public static final String TOKEN_TITLE_TEST = "resident:";

    /**
     * 过滤器白名单地址
     */
    public static final String[] INTERFACE_ADDRESS_CONSTANT = {"user/login","checkCode","resident/forgetPassword","resident/appRegister","resident/appLogin","resident/sendCode","resident/logout","deviceOnlineMessage"};

    /**
     * 人脸白名单同步正常
     */
    public static final int MANFACE_WHITELIST_SYNCHRO_STATUS_SUCCESS = 1;
    /**
     * 人脸白名单同步待同步
     */
    public static final int MANFACE_WHITELIST_SYNCHRO_STATUS_NOT_SYNCHRO = 0;
    /**
     * 人脸白名单同步待停用
     */
    public static final int MANFACE_WHITELIST_SYNCHRO_STATUS_NOT_USE = 2;


    /**
     * 地址编码添加 用/ 拼接
     */
    public static final String ADDRESS_CODE="/";

    /**
     *  应用的类型： 1：web  使用t_menu表  2: app使用 t_function表
     */
    public static final int PERMISSION_TYPE_MENU =1;
    public static final int PERMISSION_TYPE_APP =2;


    //    人员类型 0-房东 1-代管人 2-租赁人
    public static final int OWNER_TYPE=0;
    public static final int CUSTODIAN_TYPE=1;
    public static final int RENT_TYPE=2;

    /**
     * 一标三实已同步
     */
    public static final int YIBIAOSANSHI_SYNCHRO_STATUS_SUCCESS = 1;
    /**
     * 一标三实未同步
     */
    public static final int YIBIAOSANSHI_SYNCHRO_STATUS_FAIL = 0;
    /**
     * 一标三实初始同步次数
     */
    public static final int YIBIAOSANSHI_INITIAL_SYNCHRO_COUNT = 0;

    /**
     * 一标三实公司拥有
     */
    public static final int YIBIAOSANSHI_COMPANY_OWNED=0;
    /**
     * 一标三实非公司拥有
     */
    public static final int YIBIAOSANSHI_COMPANY_OWNED_NOT=1;
    /**
     * 一标三实房屋出租
     */
    public static final int YIBIAOSANSHI_RENT=0;
    /**
     * 一标三实房屋不出租
     */
    public static final int YIBIAOSANSHI_RENT_NOT=1;
    /**
     * 房屋用途 租用
     */
    public static final int HOUSE_USAGE_FOR_RENT=1068;
    /**
     * 房屋用途 其他租借用
     */
    public static final int HOUSE_USAGE_FOR_RENT_OR_OTHER=1070;


    /**
     * resident状态 未授权
     */
    public static final int RESIDENT_STATUS_UNAUTHORIZE = 1;
    /**
     * resident状态 正常
     */
    public static final int RESIDENT_STATUS_AUTHORIZE = 0;
    /**
     * resident状态 需要完善
     */
    public static final int RESIDENT_STATUS_NEED_FILL = 2;

    /**
     * 车辆白名单状态已同步
     */
    public static final Integer CARWHITELIST_SYNCHRO_STATUS_SUCCESS = 1;

    /**
     * 车辆白名单状态待同步
     */
    public static final Integer CARWHITELIST_SYNCHRO_STATUS_WAIT = 0;

    /**
     * 车辆白名单状态停用
     */
    public static final Integer CARWHITELIST_SYNCHRO_STATUS_STOP = 2;
    /**
     * 杨柳青编码
     */
    public static final String AREA_CODE = "120111101000";

    /**
     * 菜单是否显示  1 显示
     */
    public static final  int  show =1;

    /**
     * 菜单是否显示  0  不显示
     */
    public static final  int  display=0;

    /**
     * 设置密码重置后的值
     */
    public static final String resetPassword = "12345678";

    /**
     * AOP过滤白名单地址
     */
    public static final String[] AOP_URI = {"/address/excel","/checkCode/getCode"};
    /**
     * 居民信息修改  操作类型 更新
     */
    public static final int ACTION_TYPE_UPDATE=0;
    /**
     * 居民信息修改  操作类型 删除
     */
    public static final int ACTION_TYPE_DELETE=1;
    /**
     * 居民信息修改  操作类型 插入
     */
    public static final int ACTION_TYPE_INSERT=3;
    /**
     * 居民信息修改  操作类型 不变
     */
    public static final int ACTION_TYPE_NOT_CHANGE=2;
    /**
     *超级管理员id
     */
    public static  final Long ADMIN_ID=1L;
    /**
     * 设备类型：车闸
     */
    public static final int EQUIPMENT_TYPE=4;
    /**
     * 设备类型：组关系 人:0,设备:1
     */
    public static final int GROUP_REL_TYPE_MAN=0;
    public static final int GROUP_REL_TYPE_DEVICE=1;


    public static final String REDIS_KEY_FACE_CLOUD_TOKEN  = "faceCloudToken:";
    //第三方平台性别 0-女 1-男
    public static final int PLATFORM_SEX_FEMALE=0;
    public static final int PLATFORM_SEX_MALE=1;
    /**
     * 每日启用时间
     */
    public static final String DAY_BEGIN_TIME="00:00:00";
    /**
     * 每日结束时间
     */
    public static final String DAY_END_TIME="23:59:59";
    /**
     * 工作天数（第三方用）
     */
    public static final List<String> WEEKDAYS = Arrays.asList("0","1","2","3","4","5","6");
    /**
     * 工作天数（入自己库用t_group）
     */
    public static final String LOCAL_WEEKDAYS = "0,1,2,3,4,5,6";
    /**
     * 地址级别
     */
    public static int ADDRESS_LEVEL_ZHEN = 0;
    /**
     * 地址级别
     */
    public static int ADDRESS_LEVEL_XIAOQU = 1;
    /**
     * 杨柳青的id
     *
     */
    public static final long YANG_LIU_QING_ID = 0L;
    /**
     * 分组中间表关系类型： 人:0,设备:1
     */
    public static final int GROUP_REL_RESIDENT_TYPE=0;
    public static final int GROUP_REL_EQUIPMENT_TYPE=1;
    /**
     * 文件名
     */
    public static final String BOOTSTRAP_FILE = "bootstrap.yml";

    public static final String AREA_CODE_ADDRESS="0";
    //平台需求的orgEmail
//    public static final String PLATFORM_ORG_EMAIL="729763190@qq.com";
    //平台需求的validateEnd
    public static final String PLATFORM_VALIDATE_DATE_END="2200-12-31";

}
