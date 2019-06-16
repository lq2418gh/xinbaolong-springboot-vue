package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.UserRelPhone;
import com.bit.sc.module.sys.vo.UserRelPhoneVO;

/**
 * UserRelPhone管理的Dao
 * @author 
 *
 */
public interface UserRelPhoneMapper {
	/**
	 * 根据条件查询UserRelPhone
	 * @param userRelPhoneVO
	 * @return
	 */
	public List<UserRelPhone> findByConditionPage(UserRelPhoneVO userRelPhoneVO);
	/**
	 * 查询所有UserRelPhone
	 * @return
	 */
	public List<UserRelPhone> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个UserRelPhone
	 * @param id	 	 
	 * @return
	 */
	public UserRelPhone findById(@Param(value = "id") Long id);
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
	 * @param userRelPhones
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除UserRelPhone
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
}
