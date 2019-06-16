package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.Menu;
import com.bit.sc.module.sys.vo.MenuVO;

/**
 * Menu管理的Dao
 * @author 
 *
 */
public interface MenuMapper {
	/**
	 * 根据条件查询Menu
	 * @param menuVO
	 * @return
	 */
	public List<Menu> findByConditionPage(MenuVO menuVO);
	/**
	 * 查询所有Menu
	 * @return
	 */
	public List<Menu> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Menu
	 * @param id	 	 
	 * @return
	 */
	public Menu findById(@Param(value = "id") Long id);
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
	 * @param ids
	 */
	public void batchDelete(@Param(value = "ids")List<Long> ids);
	/**
	 * 删除Menu
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * @description:
	 * @author liyujun
	 * @date 2018-10-29
	 * @param roleIds :
	 * @param applicationCode :
	 * @return : List<MenuVO>
	 */
	public List<Menu> findMenuByCondition(@Param(value = "roleIds") List<Long> roleIds, @Param(value = "applicationCode") String applicationCode, @Param(value="isDisplay") Integer isDisplay , @Param(value = "type") Integer type);
	/**
	 * 根据menuCode 查询统计
	 * @param menuCode
	 * @return
	 */
	public int findCountByMenuCode(String menuCode);

	/**
	 * 根据code
	 * @return
	 * @param applicationCode
	 */
	List<Menu> findByApplicationCode(String applicationCode);

	List<Menu>  findAllByParam(Menu menu);
}
