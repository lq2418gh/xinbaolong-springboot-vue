package com.bit.sc.module.user.dao;

import java.util.List;

import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.user.pojo.UserRelAddress;
import com.bit.sc.module.user.pojo.UserRole;
import com.bit.sc.module.user.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.user.pojo.User;
import com.bit.sc.module.user.vo.UserVO;

/**
 * User管理的Dao
 * @author 
 *
 */
public interface UserMapper {
	/**
	 * 根据条件查询User
	 * @param userVO
	 * @return
	 */
	public List<User> findByConditionPage(UserVO userVO);
	/**
	 * 查询所有User
	 * @return
	 */
	public List<User> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个User
	 * @param id	 	 
	 * @return
	 */
	public User findById(@Param(value = "id") Long id);

	/**
	 * 通过主键查询单个User
	 * @param id
	 * @return
	 */
	public List<UserRole> findRoleById(UserRoleVO userRoleVO);

	/**
	 * 批量保存User
	 * @param users
	 */
	public void batchAdd(List<User> users);
	/**
	 * 保存User
	 * @param user
	 */
	public void add(User user);
	/**
	 * 批量更新User
	 * @param users
	 */
	public void batchUpdate(List<User> users);
	/**
	 * 更新User
	 * @param user
	 */
	public void update(User user);
	/**
	 * 删除User
	 * @param
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除User
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 计算有相同username的数量
	 * @param username
	 * @return
	 */
	Integer countSameUserName(@Param(value = "username") String username);

	List<User> searchUser(@Param(value = "username") String username,
						  @Param(value = "mobile") String mobile,
						  @Param(value = "insertTimeStart") String insertTimeStart,
						  @Param(value = "insertTimeEnd") String insertTimeEnd);

	/**
	 *根据参数查询
	 * @param userVO
	 * @auther zhangjie
	 * @date 2018-11-20
	 */
	public List<UserVO> findByParam(UserVO userVO);

	/**
	 *根据用户id查询地址
	 * @param userVO
	 * @auther zhangjie
	 * @date 2018-11-20
	 */
	public List<Address> findAddressByUserId(UserVO userVO);

    /**
     *根据用户id查询地址
     * @param id
     * @auther zhangjie
     * @date 2018-11-20
     */
    public List<Address> findAddress(List<Long> id);

	/**
	 * 分配小区
	 * @author zhangjie
	 * @date 2018-12-06
	 */
    public void distributeAddress(List<UserRelAddress> userRelAddresses);

	/**
	 * 根据用户id返显地址信息
	 * @return baseVo
	 * @author zhangjie
	 * @date 2018-12-07
	 */
	List<Long> findAddressIdByUserId(Long userId);

    /**
     * 根据用户id删除记录
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-08
     */
	void deleteByUserId(Long userId);


}
