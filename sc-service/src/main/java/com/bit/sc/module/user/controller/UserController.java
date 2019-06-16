package com.bit.sc.module.user.controller;

import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;
import com.bit.common.ResultCode;

import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.vo.UserListSearchVo;
import com.bit.sc.module.user.pojo.User;
import com.bit.sc.module.user.service.UserService;
import com.bit.sc.module.user.vo.UserAddressVO;
import com.bit.sc.module.user.vo.UserRoleVO;
import com.bit.sc.module.user.vo.UserVO;
import com.bit.utils.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * User的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CacheUtil cacheUtil;

    /**
     * 分页查询User列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody UserVO userVO) {
    	//分页对象，前台传递的包含查询的参数

        return userService.findByConditionPage(userVO);
    }

    /**
     * userList页面搜索功能
     * @return
     */
    @PostMapping("/search")
    public BaseVo userSearch(@RequestBody UserListSearchVo userListSearchVo){
        return userService.userSearch(userListSearchVo);
    }

    /**
     * 根据主键ID查询User
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(user);
		return baseVo;
    }


    /**
     * 根据主键ID查询User的角色
     *@PathVariable(value = "id") Long id,
     * @param userRoleVO
     * @return
     */
    @PostMapping("/queryRole")
    public BaseVo queryRole(@RequestBody UserRoleVO userRoleVO) {
        return userService.findRoleById(userRoleVO);
    }


    /**
     * 新增User
     *
     * @param userVO UserVO
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@RequestBody UserVO userVO) {
        BaseVo baseVo = new BaseVo();
        baseVo = userService.add(userVO);
        return baseVo;
    }
    /**
     * 修改User
     *
     * @param user User实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody User user) {
		userService.update(user);
	    BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除User
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {

		BaseVo baseVo = userService.delete(id);
        return baseVo;
    }

    /**
     * 根据UserID集合批量删除User
     *
     * @param ids UserID集合
     * @return BaseVo
     *
     */
    @PostMapping("/delBatchByIds")
    public BaseVo delBatchByIds(@RequestBody List<Long> ids) {
        userService.batchDelete(ids);
		BaseVo baseVo = new BaseVo();
        return baseVo;
   }

	@PostMapping("/login")
	public BaseVo login(@RequestBody UserVO userVO) {
        BaseVo baseVo = userService.login(userVO);
        return baseVo;
	}

	@PostMapping("/logout")
	public BaseVo logout(@RequestBody UserVO userVO) {
        BaseVo baseVo =null;
        try {
            baseVo = userService.logout(userVO);
        }catch (Exception e){
            baseVo.setCode(ResultCode.WRONG.getCode());
            baseVo.setMsg(ResultCode.WRONG.getInfo());
        }
		return baseVo;
	}

    /**
     * 注册新用户时的预先校验用户名称是否占用
     * @param username
     * @return
     */
    @GetMapping("/countUserName/{username}")
    public BaseVo queryUserNameIfOccupied(@PathVariable(value = "username") String username) {
        BaseVo baseVo = new BaseVo();
        if (userService.countSameUserName(username) > 0) {
            throw new BusinessException("用户名被占用");
        }


        return baseVo;
    }

    @PostMapping("/editUserInfo")
    public BaseVo editUserInfo(@RequestBody UserVO userVO){
        BaseVo baseVo =new BaseVo();
        baseVo = userService.editUserInfo(userVO);
        baseVo = userService.editUserInfo(userVO);
        return baseVo;
    }

    /**
     * 重置密码
     * @param id
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-4
     */
    @GetMapping("/resetPassword/{id}")
    public BaseVo resetPassword(@PathVariable("id") Long id){
        BaseVo baseVo = new BaseVo();
        userService.resetPassword(id);
        return baseVo;
    }

    /**
     * 修改密码
     * @param userVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-4
     */
    @PostMapping("/modifyPassword")
    public BaseVo modifyPassword(@RequestBody UserVO userVO){
        BaseVo baseVo = new BaseVo();
        userService.modifyPassword(userVO);
        userService.logout(userVO);
        return baseVo;
    }

    /**
     * 获取当前登录用户地址下拉选列表
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-06
     */
    @GetMapping("/userAddressList")
    public BaseVo userAddressList(){
        BaseVo baseVo = new BaseVo();
        baseVo.setData(userService.userAddressList());
        return baseVo;
    }

    /**
     * 返回登陆用户的个人信息
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-06
     */
    @GetMapping("/currentUserInfo")
    public BaseVo currentUserInfo(){
        BaseVo baseVo = new BaseVo();
        baseVo.setData(userService.currentUserInfo());
        return baseVo;
    }

    /**
     * 从当前登录用户地址下拉选列表中选择地址
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-06
     */
    @PostMapping("/userAddress")
    public BaseVo userAddress(@RequestBody UserAddress userAddress){
        BaseVo baseVo = new BaseVo();
        userService.userAddress(userAddress);
        return baseVo;
    }

    /**
     * 分配小区
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-06
     */
    @PostMapping("/distributeAddress")
    public BaseVo distributeAddress(@RequestBody UserAddressVO userAddressVO){
        BaseVo baseVo = new BaseVo();
        userService.distributeAddress(userAddressVO);
        return baseVo;
    }

    /**
     * 根据用户id返显地址信息
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-07
     */
    @GetMapping("/findAddressByUserId/{id}")
    public BaseVo findAddressByUserId(@PathVariable("id") Long userId){
        BaseVo baseVo = new BaseVo();
        baseVo.setData(userService.findAddressIdByUserId(userId));
        return baseVo;
    }

}
