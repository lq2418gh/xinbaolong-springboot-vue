package com.bit.sc.module.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.bit.base.service.BaseService;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.dao.RoleRelPermissionMapper;
import com.bit.sc.module.sys.pojo.RoleRelPermission;
import com.bit.sc.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.Menu;
import com.bit.sc.module.sys.vo.MenuVO;
import com.bit.sc.module.sys.dao.MenuMapper;
import com.bit.sc.module.sys.service.MenuService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Menu的Service实现类
 * @author codeGenerator
 *
 */
@Service("menuService")
public class MenuServiceImpl extends BaseService implements MenuService  {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private RoleRelPermissionMapper roleRelPermissionMapper;
	
	/**
	 * 根据条件查询Menu
	 * @param
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(MenuVO menuVO){
        BaseVo baseVo = new BaseVo();
		PageHelper.startPage(menuVO.getPageNum(), menuVO.getPageSize());
		List<Menu> list = menuMapper.findByConditionPage(menuVO);
		PageInfo<Menu> pageInfo = new PageInfo<Menu>(list);
        baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有Menu
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Menu> findAll(String sorter){
		return menuMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Menu
	 * @param id
	 * @return
	 */
	@Override
	public Menu findById(Long id){
		return menuMapper.findById(id);
	}
	
	/**
	 * 保存Menu
	 * @param menu
	 */
	@Override
    @Transactional
	public void add(Menu menu){
		if (menu.getType()==0){
			this.check(menu);
			if (menu.getMenuLevel()!=Const.IS_DISPLAY_Y){
				menu.setIsDisplay(Const.IS_DISPLAY_Y);
			}else{
				menu.setIsDisplay(Const.IS_DISPLAY_N);
			}
		}else{
			menu.setIsDisplay(Const.IS_DISPLAY_Y);
		}
		menu.setCreateTime(new Date());
		menu.setCreateUserId(getCurrentUserInfo().getId());
		menuMapper.add(menu);
	}
	/**
	 * 更新Menu
	 * @param menu
	 */
	@Override
    @Transactional
	public void update(Menu menu){
        this.check(menu);
		menu.setUpdateTime(new Date());
		menu.setUpdateUserId(getCurrentUserInfo().getId());
		menuMapper.update(menu);
	}
	/**
	 * 删除Menu
	 * @param ids
	 */
	@Override
    @Transactional
	public void batchDelete( List<Long> ids){
		menuMapper.batchDelete(ids);
	}


	/**
	 * 删除Menu
	 * @param id
	 */
	@Override
    @Transactional
	public void delete(Long id){
		menuMapper.delete(id);
        RoleRelPermission roleRelPermission = new RoleRelPermission();
        roleRelPermission.setFunctionId(id);
        roleRelPermission.setPermissionType(Const.PERMISSION_TYPE_MENU);
        roleRelPermissionMapper.deleteByFunction(roleRelPermission);
	}

	/**
	 * 根据用户得到菜单
	 * @param userId :
	 * @param apply_code :
	 * @param  type ：0是菜单 1是按钮  null 是所有
	 * @return
	 */
	@Override
	public List<Menu> findMenuByCondition(Long userId, String apply_code, Integer type) {
		UserInfo userInfo=getCurrentUserInfo();
        List<Menu> menuList =null;
        if (userInfo.getId()==1l){
            Menu menu = new Menu();
            menu.setApplicationName(apply_code);
            menu.setIsDisplay(Const.show);
            menuList = menuMapper.findAllByParam(menu);
        }else{
            List<Long> roleIds= userInfo.getRoles();
            menuList=menuMapper.findMenuByCondition(roleIds,apply_code,Const.show,type);
        }
        List<Menu> rootMenu=new ArrayList<>();

		menuList.stream().forEach(menu -> {
			if(menu.getPid().equals(Const.ROOT_MENU_PID)){
				rootMenu.add(menu);
			}
		});
        List<Menu> finalMenuList = menuList;
        rootMenu.stream().forEach(menu -> {
			menu.setChildMenus(getChild(menu.getId(), finalMenuList));
		});
		return rootMenu;
	}
	/**
	 * 根据menuCode 查询统计
	 * @param menuCode
	 * @return
	 */
	@Override
	public int findCountByMenuCode(String menuCode) {
		return menuMapper.findCountByMenuCode(menuCode);
	}

    /**
     * 根据applicationCode 查询 菜单  返回树结构 数据
     * @param applicationCode
     * @return
     */
	@Override
	public List<Menu> findMenuTree(String applicationCode) {
        List<Menu> menuMapperAll = menuMapper.findByApplicationCode(applicationCode);
        List<Menu> rootMenu=new ArrayList<>();
        for (Menu menu : menuMapperAll) {
            if (menu.getPid()==Const.ROOT_MENU_PID){
                rootMenu.add(menu);
            }
        }
        for (Menu menu : rootMenu) {
            menu.setChildMenus(getMenuChild(menu.getId(), menuMapperAll));
        }
        return rootMenu;
	}

	/**
	 * 递归查找子菜单
	 *
	 * @param id
	 *            当前菜单id
	 * @param menuList
	 *            菜单数据
	 * @return
	 */
	private List<Menu> getChild(Long id, List<Menu> menuList) {
		// 子菜单
		List<Menu> childList = new ArrayList<>();
		for (Menu menu : menuList) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (menu.getPid()!=null) {
				if (menu.getPid().equals(id)) {
					childList.add(menu);
				}
			}
		}
		// 把子菜单的子菜单再循环一遍
		for (Menu menu : childList) {// 没有url子菜单还有子菜单
//			if (menu.getMenuUrl()==null || "".equals(menu.getMenuUrl())) {
				// 递归
				menu.setChildMenus(getChild(menu.getId(), menuList));
//			}
		} // 递归退出条件
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}
    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param menuList
     *            菜单数据
     * @return
     */
    private List<Menu> getMenuChild(Long id, List<Menu> menuList) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : menuList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getPid()!=null) {
                if (menu.getPid().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {// 没有url子菜单还有子菜单
//			if (menu.getMenuUrl()==null || "".equals(menu.getMenuUrl())) {
            // 递归
            menu.setChildMenus(getMenuChild(menu.getId(), menuList));
//			}
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 校验非空字段--保存—更新
     * @param menu
     */
	public  void  check(Menu menu){
        CheckUtil.notEmpty(menu.getMenuName().trim(),"菜单名称不能为空");
        CheckUtil.notEmpty(menu.getMenuCode().trim(),"菜单编码不能为空");
        CheckUtil.notNull(menu.getApplicationId(),"应用编码不能为空");
        CheckUtil.notNull(menu.getPid(),"菜单父节点不能为空");
        CheckUtil.notNull(menu.getIsDisplay(),"是否显示是非空字段");
    }

}
