package com.bit.sc.module.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.UserRelRole;
import com.bit.sc.module.sys.vo.UserRelRoleVO;

/**
 * UserRelRole管理的Dao
 *
 * @author
 */
public interface UserRelRoleMapper {
    /**
     * 根据条件查询UserRelRole
     *
     * @param userRelRoleVO
     * @return
     */
    public List<UserRelRole> findByConditionPage(UserRelRoleVO userRelRoleVO);

    /**
     * 查询所有UserRelRole
     *
     * @return
     */
    public List<UserRelRole> findAll(@Param(value = "sorter") String sorter);

    /**
     * 通过主键查询单个UserRelRole
     *
     * @param id
     * @return
     */
    public UserRelRole findById(@Param(value = "id") Long id);

    /**
     * 批量保存UserRelRole
     *
     * @param userRelRoles
     */
    public void batchAdd(@Param("userRelRoles") List<UserRelRoleVO> userRelRoles);

    /**
     * 保存UserRelRole
     *
     * @param userRelRole
     */
    public void add(UserRelRole userRelRole);

    /**
     * 批量更新UserRelRole
     *
     * @param userRelRoles
     */
    public void batchUpdate(List<UserRelRole> userRelRoles);

    /**
     * 更新UserRelRole
     *
     * @param userRelRole
     */
    public void update(UserRelRole userRelRole);

    /**
     * 删除UserRelRole
     *
     * @param ids
     */
    public void batchDelete(List<Long> ids);

    /**
     * 删除UserRelRole
     *
     * @param id
     */
    public void delete(@Param(value = "id") Long id);

    /**
     * @param userId :
     * @return : java.util.List<com.bit.sc.module.sys.pojo.UserRelRole>
     * @description:
     * @author liyujun
     * @date 2018-10-30
     */
    public List<UserRelRole> findRoleByCondition(@Param(value = "userId") Long userId);

    /**
     * 根据人物id删除人物角色关系表中的关系
     *
     * @param id
     */
    public void deleteRole(@Param(value = "id") Long id);

}
