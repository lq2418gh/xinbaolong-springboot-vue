package com.bit.sc.module.resident.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.resident.pojo.Resident;
import com.bit.sc.module.resident.vo.ResidentBatchAuthorizeVO;
import com.bit.sc.module.resident.vo.ResidentMobileVO;
import com.bit.sc.module.resident.vo.ResidentVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResidentService {
    BaseVo getSmsCode(ResidentVO residentVO);

    BaseVo login(ResidentVO residentVO);


    /**
     * app用户注册业务
     * @param
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-13
     */
    BaseVo appRegister(ResidentMobileVO residentMobileVO);

    /**
     * app用户登陆业务
     * @param  residentMobileVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-13
     */
    BaseVo appLogin(ResidentMobileVO residentMobileVO);

    /**
     * app用户登陆业务
     * @param  residentMobileVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-14
     */
    BaseVo logout(ResidentMobileVO residentMobileVO);

    /**
     * app修改密码业务
     * @param  residentMobileVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-14
     */
    BaseVo forgetPassword(ResidentMobileVO residentMobileVO);

    /**
     * app 发送验证码
     * @param  residentMobileVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-14
     */
     BaseVo sendVaildCode(ResidentMobileVO residentMobileVO);

    /**
     * 查询所有居民
     * @param sorter
     * @return List<Resident>
     * @author zhangjie
     * @date 2018-11-26
     */
    List<Resident> findAll(String sorter);


    /**
     * 保存Resident
     * @param residentVO
     * @author chenduo
     */
    void add(ResidentVO residentVO);

    /**
     * 更新Resident
     * @param residentVO
     * @author chenduo
     */
    void update(ResidentVO residentVO);

    /**
     * 删除Resident
     * @param id
     * @author chenduo
     */
    void delete(Long id);
    /**
     *
     * 功能描述:上传照片
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:39
     */

    BaseVo uploadpicture(MultipartFile file);
    /**
     * 联合居民地址表 地址表 居民手机表 附件表
     *
     * @param
     * @return
     * @author chenduo
     */
    BaseVo queryResidentInfoByPage(ResidentVO residentVO);
    /**
     *
     * 功能描述:授权人脸白名单
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 9:42
     */
    BaseVo authorize(ResidentVO residentVO);

    /**
     *
     * 功能描述:编辑的时候反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/29 9:04
     */
    BaseVo reflect(Long id);
    /**
     *
     * 功能描述: 批量导入
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:38
     */

    BaseVo batchImport();
    /**
     *
     * 功能描述: 批量授权
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:38
     */

    BaseVo batchAuthorize(ResidentBatchAuthorizeVO residentBatchAuthorizeVO);

    /**
     *
     * 功能描述: 取消授权
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:38
     */

    BaseVo revoke(ResidentVO residentVO);

    /**
     *
     * 功能描述: 批量取消授权
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:38
     */

    BaseVo batchRevoke(ResidentBatchAuthorizeVO residentBatchAuthorizeVO);
    /**
     *
     * 功能描述: 授权反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/14 14:14
     */

    BaseVo authorizationReflect(Long id);
}
