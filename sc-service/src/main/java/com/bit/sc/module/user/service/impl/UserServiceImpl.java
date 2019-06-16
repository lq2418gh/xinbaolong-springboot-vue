package com.bit.sc.module.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.dao.RoleMapper;

import com.bit.sc.module.sys.dao.UserRelRoleMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.service.InterfacePermissionService;
import com.bit.sc.module.sys.vo.AddressVO;
import com.bit.sc.module.sys.vo.UserListSearchVo;
import com.bit.sc.module.user.dao.UserMapper;
import com.bit.sc.module.user.pojo.User;
import com.bit.sc.module.user.pojo.UserRelAddress;
import com.bit.sc.module.user.pojo.UserRole;
import com.bit.sc.module.user.service.UserService;
import com.bit.sc.module.user.vo.UserAddressVO;
import com.bit.sc.module.user.vo.UserRoleVO;
import com.bit.sc.module.user.vo.UserVO;
import com.bit.sc.utils.DateUtil;
import com.bit.utils.CacheUtil;
import com.bit.utils.MD5Util;
import com.bit.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User的Service实现类
 *
 * @author codeGenerator
 */
@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private InterfacePermissionService interfacePermissionService;
    @Autowired
    private UserRelRoleMapper userRelRoleMapper;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 根据条件查询User
     *
     * @param
     * @return
     */
    @Override
    public BaseVo findByConditionPage(UserVO userVO) {
        BaseVo baseVo = new BaseVo();
        UserInfo userInfo = getCurrentUserInfo();
        PageHelper.startPage(userVO.getPageNum(), userVO.getPageSize());
        List<User> list = userMapper.findByConditionPage(userVO);

        PageInfo<User> pageInfo = new PageInfo<User>(list);
        baseVo.setData(pageInfo);
        return baseVo;
    }

    @Override
    public BaseVo login(UserVO userVO) {
        BaseVo baseVo = new BaseVo();
        UserInfo userInfo = new UserInfo();
        UserVO cond = new UserVO();
        cond.setUsername(userVO.getUsername());
        List<User> list = userMapper.findByConditionPage(cond);

        Map<String, Object> rss = new HashMap();
        Map<String, Object> userData = new HashMap<>();

        //前期开发,先屏蔽了,节省时间
//		String captcha = (String)cacheUtil.get(Const.REDIS_KEY_CAPTCHA+userVO.getCaptcha());
//		if (userVO.getCaptcha() == null
//				|| !userVO.getCaptcha().equals(captcha) ) {
//			throw new BusinessException("验证码不正确");
//		}
        if (list.size() > 0) {
            User user = list.get(0);
//User user = userMapper.findById(userVO.getId());
            String pw = MD5Util.compute(userVO.getPassword());
            if (!pw.equals(user.getPassword())) {
                throw new BusinessException("用户名或密码不正确！");
            } else {
                //判断超管
                if (1==user.getId()){
                    String token = "user:" + UUIDUtil.getUUID();
                    userInfo.setId(user.getId());
                    userInfo.setUserName(user.getUsername());
                    userInfo.setToken(token);
                    String json = JSON.toJSONString(userInfo);
                    cacheUtil.set(token, json, Const.TOKEN_EXPIRE_SECONDS);

                    rss.put("token", token);
                    userData.put("user", user);
                    rss.put("userInfo", userData);
                    baseVo.setData(rss);
                    return baseVo;
                }
                //根据应用code和用户id查询角色
                cond.setApplicationCode(userVO.getApplicationCode());
                cond.setUserId(user.getId().longValue());
                List<UserVO> uv = userMapper.findByParam(cond);
                if (uv.size() > 0) {
                    //查询所有的角色
                    List<Long> roleList = roleMapper.findRoleByUserId(user.getId());
                    for (Long aLong : roleList) {
                        interfacePermissionService.findPermissionById(aLong);
                    }
                    String token = "user:" + UUIDUtil.getUUID();
                    userInfo.setId(user.getId());
                    userInfo.setUserName(user.getUsername());
                    userInfo.setToken(token);
                    userInfo.setRoles(roleList);

                    //根据用户名查询地址
                    List<UserAddress> userAddresses =new ArrayList<UserAddress>();
                    List<Address> addresses = userMapper.findAddressByUserId(cond);
                    //判断该用户是否绑定地址
                    if (0==addresses.size()){
                        throw new BusinessException("该用户未分配地址，不能登录！");
                    }
                    //可能单选一个小区或杨柳青镇
                    if (1==addresses.size()){
                        int addressLevel = addresses.get(0).getAddressLevel();
                        if (Const.ADDRESS_LEVEL_ZHEN==addressLevel){
                            //杨柳青
                            UserAddress userAddress = new UserAddress();
                            userAddress.setId(addresses.get(0).getId());
                            userAddress.setAddressCode(addresses.get(0).getAddressCode());
                            userAddress.setAddressName(addresses.get(0).getAddressName());
                            userAddresses.add(userAddress);

                            AddressVO vo = new AddressVO();
                            vo.setAddressLevel(Const.ADDRESS_LEVEL_XIAOQU);
                            List<Address> res = addressMapper.findByConditionPage(vo);
                            for (Address addr : res){
                                UserAddress ua = new UserAddress();
                                ua.setId(addr.getId());
                                ua.setAddressCode(addr.getAddressCode());
                                ua.setAddressName(addr.getAddressName());
                                userAddresses.add(ua);
                            }
                        }else {
                            //单选一个小区
                            UserAddress uadd = new UserAddress();
                            uadd.setAddressName(addresses.get(0).getAddressName());
                            uadd.setAddressCode(addresses.get(0).getAddressCode());
                            uadd.setId(addresses.get(0).getId());
                            userAddresses.add(uadd);
                        }
                    }else {
                        //多个小区
                        for (Address addr : addresses){
                            UserAddress ua = new UserAddress();
                            ua.setId(addr.getId());
                            ua.setAddressCode(addr.getAddressCode());
                            ua.setAddressName(addr.getAddressName());
                            userAddresses.add(ua);
                        }
                    }
                    userInfo.setUserAddresses(userAddresses);
                    //userinfo中放一个默认的地址对象
                    if (userAddresses.size()>0){
                        userInfo.setCurrentUserAddresses(userAddresses.get(0));
                    }else {
                        userInfo.setCurrentUserAddresses(null);
                    }

                    String json = JSON.toJSONString(userInfo, SerializerFeature.DisableCircularReferenceDetect);
                    cacheUtil.set(token, json, Const.TOKEN_EXPIRE_SECONDS);
                    rss.put("token", token);
                    userData.put("user", user);
                    userData.put("roles", roleList);
                    userData.put("address", userAddresses);
                    if (userAddresses.size()>0){
                        userData.put("currentUserAddresses",userAddresses.get(0));
                    }else {
                        userData.put("currentUserAddresses",null);
                    }
                    rss.put("userInfo", userData);
                    baseVo.setData(rss);
                } else {
                    throw new BusinessException("无角色无法登录！");
                }
            }
        } else {
            throw new BusinessException("用户名或密码不正确！");
        }
        return baseVo;
    }

    @Override
    public BaseVo logout(UserVO userVo) {
        UserInfo userInfo = getCurrentUserInfo();
        cacheUtil.del(userInfo.getToken());
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 重置密码
     * @param id
     */
    @Override
    @Transactional
    public void resetPassword(Long id) {
        User user = userMapper.findById(id);
        String pwd = MD5Util.compute(Const.resetPassword);
        user.setPassword(pwd);
        userMapper.update(user);
    }

    /**
     * 修改密码
     * @param userVO
     */
    @Override
    public void modifyPassword(UserVO userVO) {
        UserVO vo = new UserVO();
        String pwd = MD5Util.compute(userVO.getPassword());
        vo.setPassword(pwd);
        //获取当前登录用户的id
        Long userId = getCurrentUserInfo().getId();
        User user = userMapper.findById(userId);
        if (!user.getPassword().equals(pwd)) {
            throw new BusinessException("原密码输入不正确！");
        }else {
            if (!userVO.getNewPassword().equals(userVO.getSureNewPassword())){
                throw new BusinessException("两次输入的新密码不一致！");
            }else {
                User user1 = new User();
                user1.setId(userId);
                user1.setPassword(MD5Util.compute(userVO.getNewPassword()));
                userMapper.update(user1);
            }
        }
    }

    /**
     * 获取用户地址列表
     * @return list
     * @author zhangjie
     * @date 2018-12-06
     */
    @Override
    public List<UserAddress> userAddressList() {
        List<UserAddress> list = getCurrentUserInfo().getUserAddresses();
        return list;
    }

    /**
     * 返回登录用户的个人信息
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-06
     */
    @Override
    public UserInfo currentUserInfo() {
        UserInfo userInfo = getCurrentUserInfo();
        return userInfo;
    }

    /**
     * 从当前登录用户地址下拉选列表中选择地址
     * @return ua
     * @author zhangjie
     * @date 2018-12-06
     */
    @Override
    public void userAddress(UserAddress userAddress) {
        //查出token
        String key = getCurrentUserInfo().getToken();
//        Long time = cacheUtil.getExpire(key);
        //更新token
        String a = (String) cacheUtil.get(key);
        JSONObject json = JSONObject.parseObject(a);
        json.put("currentUserAddresses",userAddress);
        String res = JSON.toJSONString(json);
        cacheUtil.set(key,res,Const.TOKEN_EXPIRE_SECONDS);
    }

    /**
     * 分配小区
     * @author zhangjie
     * @date 2018-12-06
     */
    @Override
    public void distributeAddress(UserAddressVO userAddressVO) {
        //判断是否有地址id
        if (userAddressVO.getAddressIds().size()>0){
            List<UserRelAddress> ualist = new ArrayList<>();
            List<Long> list = userAddressVO.getAddressIds();
            for (Long uid : list){
                UserRelAddress urd = new UserRelAddress();
                urd.setAddressId(uid);
                urd.setUserId(userAddressVO.getUserId());
                ualist.add(urd);
            }
            //先清空再插入
            userMapper.deleteByUserId(userAddressVO.getUserId());
            userMapper.distributeAddress(ualist);
        }else {
            userMapper.deleteByUserId(userAddressVO.getUserId());
        }
    }

    /**
     * 根据用户id返显地址信息
     * @return baseVo
     * @author zhangjie
     * @date 2018-12-07
     */
    @Override
    public List<Long> findAddressIdByUserId(Long userId) {
        List<Long> addressIds = userMapper.findAddressIdByUserId(userId);
        return addressIds;
    }

    /**
     * 计算有相同username的数量
     *
     * @param username
     * @return
     */
    @Override
    public Integer countSameUserName(String username) {
        return userMapper.countSameUserName(username);
    }

    /**
     * userlist页面搜索
     *
     * @param userListSearchVo
     * @return
     */
    @Override
    public BaseVo userSearch(UserListSearchVo userListSearchVo) {
        BaseVo baseVo = new BaseVo();
        PageHelper.startPage(userListSearchVo.getPageNum(), userListSearchVo.getPageSize());
        if (userListSearchVo.getInsertTimeStart() != "" && userListSearchVo.getInsertTimeStart() != null
                && userListSearchVo.getInsertTimeEnd() != "" && userListSearchVo.getInsertTimeEnd() != null) {
            Date start = DateUtil.parse(userListSearchVo.getInsertTimeStart());
            Date end = DateUtil.parse(userListSearchVo.getInsertTimeEnd());

            if (start.after(end)) {
                throw new BusinessException("起始时间不能小于结束时间");
            }
        }

        List<User> userList = userMapper.searchUser(userListSearchVo.getUsername(), userListSearchVo.getMobile(), userListSearchVo.getInsertTimeStart(), userListSearchVo.getInsertTimeEnd());
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        baseVo.setData(pageInfo);


        return baseVo;
    }

    @Override
    @Transactional
    public BaseVo editUserInfo(UserVO userVO) {
        BaseVo baseVo = new BaseVo();
        User user = new User();

        BeanUtils.copyProperties(userVO, user);
        //查出来version
        User temp = userMapper.findById(userVO.getId());
        //修改一次 version +1
        user.setVersion(temp.getVersion() + 1);

        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        user.setUpdateTime(date);

        userMapper.update(user);


        return baseVo;
    }


    /**
     * 查询所有User
     *
     * @param sorter 排序字符串
     * @return
     */
    @Override
    public List<User> findAll(String sorter) {
        return userMapper.findAll(sorter);
    }

    /**
     * 通过主键查询单个User
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    /**
     * 根据主键ID查询User的角色
     *
     * @return
     */
    @Override
    public BaseVo findRoleById(UserRoleVO userRoleVO) {
        PageHelper.startPage(userRoleVO.getPageNum(), userRoleVO.getPageSize());
        List<UserRole> roles = userMapper.findRoleById(userRoleVO);
        PageInfo<UserRole> pageInfo = new PageInfo<UserRole>(roles);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(pageInfo);
        return baseVo;

    }

    /**
     * 批量保存User
     *
     * @param users
     */
    @Override
    @Transactional
    public void batchAdd(List<User> users) {
        userMapper.batchAdd(users);
    }

    /**
     * 保存User
     *
     * @param userVO
     */
    @Override
    @Transactional
    public BaseVo add(UserVO userVO) {
        BaseVo baseVo = null;

        //获取当前用户
        UserInfo userInfo = getCurrentUserInfo();

        String username = userVO.getUsername();
        baseVo = new BaseVo();
        int count = userMapper.countSameUserName(username);
        if (count > 0) {
            throw new BusinessException("该用户名被占用");
        }


        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setInsertUid(userInfo.getId().intValue());


        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());

        user.setPassword(MD5Util.compute(user.getPassword()));
        Date date = DateUtil.parse(time);
        user.setInsertTime(date);
        user.setIsDel(0);
        user.setIsJob(0);
        user.setVersion(0);
        userMapper.add(user);

        return baseVo;

    }

    /**
     * 批量更新User
     *
     * @param users
     */
    @Override
    public void batchUpdate(List<User> users) {
        userMapper.batchUpdate(users);
    }

    /**
     * 更新User
     *
     * @param user
     */
    @Override
    @Transactional
    public void update(User user) {
        userMapper.update(user);
    }

    /**
     * 删除User
     *
     * @param ids
     */
    @Override
    @Transactional
    public void batchDelete(List<Long> ids) {
        userMapper.batchDelete(ids);
    }

    /**
     * 删除User
     *
     * @param id
     */
    @Override
    @Transactional
    public BaseVo delete(Long id) {
        BaseVo baseVo = new BaseVo();
        //先删除用户与角色关系表
        userRelRoleMapper.deleteRole(id);
        //删除用户表
        userMapper.delete(id);

        return baseVo;

    }


}
