package com.bit.sc.module.sys.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.Address;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenduo
 * @create 2018-12-28 9:19
 */
public interface AddressCommonService {

    /**
     *
     * 功能描述:添加小区
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/28 9:47
     */

    BaseVo villageAdd(Address address);

    /**
     *
     * 功能描述:修改小区
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/28 9:47
     */

    BaseVo villageModify(Address address);

    /**
     *
     * 功能描述:小区上传图片
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/29 9:57
     */

    BaseVo uploadFiles(MultipartFile file);

}
