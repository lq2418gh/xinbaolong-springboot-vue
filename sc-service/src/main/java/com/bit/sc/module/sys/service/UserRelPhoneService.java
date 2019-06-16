package com.bit.sc.module.sys.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.UserRelPhone;
import com.bit.sc.module.sys.vo.UserRelPhoneVO;


import java.util.List;

/**
 * @Description:
 * @Author: liyujun
 * @Date: 2018-11-10
 **/


public interface UserRelPhoneService {

    /**
     * 根据条件查询UserRelPhone
     * @param userRelPhoneVO
     * @return
     */

    public BaseVo findByConditionPage(UserRelPhoneVO userRelPhoneVO);


    /**
     * 查询所有UserRelPhone
     * @param sorter 排序字符串
     * @return
     */

    public List<UserRelPhone> findAll(String sorter);
    /**
     * 通过主键查询单个UserRelPhone
     * @param id
     * @return
     */

    public UserRelPhone findById(Long id);

    /**
     * 批量保存UserRelPhone
     * @param userRelPhones
     */

    public void batchAdd(List<UserRelPhone> userRelPhones);
    /**
     * 保存UserRelPhone
     * @param userRelPhone
     */

    public void add(UserRelPhone userRelPhone);
    /**
     * 批量更新UserRelPhone
     * @param userRelPhones
     */

    public void batchUpdate(List<UserRelPhone> userRelPhones);
    /**
     * 更新UserRelPhone
     * @param userRelPhone
     */

    public void update(UserRelPhone userRelPhone);
    /**
     * 删除UserRelPhone
     * @param ids
     */

    public void batchDelete(List<Long> ids);
    /**
     * 删除UserRelPhone
     * @param id
     */

    public void delete(Long id);
}
