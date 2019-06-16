package com.bit.sc.module.sys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.Menu;
import com.bit.sc.module.sys.vo.MenuVO;
import com.bit.base.vo.BaseVo;
/**
 * Menu的Service
 * @author codeGenerator
 */
@Service
public interface MenuService {
	/**
	 * 根据条件查询Menu
	 * @param menuVO
	 * @return
	 */
	public BaseVo findByConditionPage(MenuVO menuVO);
	/**
	 * 查询所有Menu
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<Menu> findAll(String sorter);
	/**
	 * 通过主键查询单个Menu
	 * @param id
	 * @return
	 */
	public Menu findById(Long id);
	/**
	 * 保存Menu
	 * @param menu
	 */
	public void add(Menu menu);
	/**
	 * 更新Menu
	 * @param menu
	 */
	public void update(Menu menu);
	/**
	 * 删除Menu
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除Menu
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);

	/**
	 * @description:  根据用户得到菜单
	 * @author liyujun
	 * @date 2018-10-30
	 * @param userId :
	 * @param apply_code :
	 * @return : java.util.List<com.bit.sc.module.sys.vo.MenuVO>
	 */
	public List<Menu> findMenuByCondition(Long userId, String apply_code, Integer type) ;

	/**
	 * 根据menuCode 查询统计
	 * @param menuCode
	 * @return
	 */
	public int findCountByMenuCode(String menuCode);

	/**
	 * 查询整个树  没有权限
	 * @param applicationCode
	 * @return
	 */
	List<Menu> findMenuTree(String applicationCode);
}
