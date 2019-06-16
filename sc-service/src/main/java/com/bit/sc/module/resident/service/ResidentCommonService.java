package com.bit.sc.module.resident.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.resident.vo.ResidentBatchAuthorizeVO;
import com.bit.sc.module.resident.vo.ResidentVO;
import org.springframework.web.multipart.MultipartFile;

public interface ResidentCommonService {

    /**
     * 新增Resident
     *
     * @param residentVO Resident实体
     * @return
     * @author chenduo
     */
    BaseVo add(ResidentVO residentVO);

    /**
     * 修改Resident
     *
     * @param residentVO
     * @return
     * @author chenduo
     */
    BaseVo modify(ResidentVO residentVO);

    /**
     * 功能描述:删除居民
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/14 15:14
     */

    BaseVo delete(Long id);

    /**
     * 功能描述:编辑的时候反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/29 9:04
     */
    BaseVo reflect(Long id);

    /**
     * 功能描述:授权人脸白名单
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 9:42
     */
    BaseVo authorize(ResidentVO residentVO);

    /**
     * 功能描述:批量授权
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:36
     */
    BaseVo batchAuthorize(ResidentBatchAuthorizeVO residentBatchAuthorizeVO);

    /**
     * 功能描述:取消授权白名单
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 9:42
     */
    BaseVo revoke(ResidentVO residentVO);

    /**
     * 功能描述:批量取消授权
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:36
     */
    BaseVo batchRevoke(ResidentBatchAuthorizeVO residentBatchAuthorizeVO);

    /**
     * 功能描述:批量导入
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:36
     */
    BaseVo batchImport();

    /**
     * 功能描述:授权反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/14 14:12
     */

    BaseVo authorizationReflect(Long id);
    /**
     *
     * 功能描述: 居民上传图片
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/14 17:13
     */

    BaseVo uploadpicture(MultipartFile file);
}
