package com.bit.sc.module.group.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.group.vo.GroupRelVO;
import com.bit.sc.module.group.vo.GroupResEquVo;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * GroupRel的Service
 * @author codeGenerator
 */
@Service
public interface GroupRelService {
    /**
     * 根据条件查询GroupRel
     * @param groupRelVO
     * @return
     */
    BaseVo findByConditionPage(GroupRelVO groupRelVO);
    /**
     * 根据条件查询GroupRel
     * @param groupRelVO
     * @return
     */
    BaseVo findListPage(GroupRelVO groupRelVO);
    /**
     * 查询所有GroupRel
     * @param sorter 排序字符串
     * @return
     */
    List<GroupRel> findAll(String sorter);
    /**
     * 通过主键查询单个GroupRel
     * @param id
     * @return
     */
    GroupRel findById(Long id);
    /**
     * 保存GroupRel
     * @param groupRel
     */
    void add(GroupRel groupRel);

    /**
     * 批量保存
     * @param groupRels
     */
    void batchAdd(List<GroupRel> groupRels);
    /**
     * 更新GroupRel
     * @param groupRel
     */
    void update(GroupRel groupRel);
    /**
     * 删除GroupRel
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(List<Long> ids);


    List<GroupRel> findGroupRelList(Long groupId);
    /**
     * 删除  根据groupid 和relid  和type
     */
    void delByParam(List<GroupRel> groupRel);
    /**
     * 根据 参数 查询  list
     */
    List<GroupRel> findByParam(GroupRel groupRel);
    /**
     * 分页 根据 分组id 和type 查询
     * @param groupResEquVo
     * @return
     */
    BaseVo findPageByParam(GroupResEquVo groupResEquVo);
}
