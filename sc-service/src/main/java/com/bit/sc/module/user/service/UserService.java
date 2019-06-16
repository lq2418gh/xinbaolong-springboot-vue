package com.bit.sc.module.user.service;

import java.util.List;

import com.bit.base.bean.UserAddress;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.vo.UserListSearchVo;
import com.bit.sc.module.user.pojo.UserRole;
import com.bit.sc.module.user.vo.UserAddressVO;
import com.bit.sc.module.user.vo.UserRoleVO;
import org.springframework.stereotype.Service;
import com.bit.sc.module.user.pojo.User;
import com.bit.sc.module.user.vo.UserVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User的Service
 * @author codeGenerator
 */
@Service
public interface UserService {
	/**
	 * 根据条件查询User
	 * @param userVO
	 * @return
	 */
	public BaseVo findByConditionPage(UserVO userVO);
	/**
	 * 查询所有User
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<User> findAll(String sorter);
	/**
	 * 通过主键查询单个User
	 * @param id
	 * @return
	 */
	public User findById(Long id);

	/**
	 * 通过主键查询单个User
	 * @param id
	 * @return
	 */
	public BaseVo findRoleById(UserRoleVO userRoleVO);

	/**
	 * 批量保存User
	 * @param users
	 */
	public void batchAdd(List<User> users);
	/**
	 * 保存User
	 * @param userVO
	 */
	public BaseVo add(UserVO userVO);
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
	 * @param id
	 */
	public BaseVo delete(Long id);
	/**
	 * 批量删除User
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);

	public BaseVo login(UserVO userVO) ;

	BaseVo logout(UserVO userVO);

	Integer countSameUserName(String username);

	BaseVo userSearch(@RequestBody UserListSearchVo userListSearchVo);

	BaseVo editUserInfo(UserVO userVO);

	void resetPassword(Long id);

	void modifyPassword(UserVO userVO);

	List<UserAddress> userAddressList();

	UserInfo currentUserInfo();

	void userAddress(UserAddress userAddress);

	void distributeAddress(UserAddressVO userAddressVO);

	List<Long> findAddressIdByUserId(Long userId);

}
