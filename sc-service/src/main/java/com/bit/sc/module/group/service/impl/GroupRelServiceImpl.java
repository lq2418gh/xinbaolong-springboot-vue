package com.bit.sc.module.group.service.impl;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.module.equipment.dao.EquipmentMapper;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.group.dao.GroupRelMapper;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.group.pojo.GroupResEqu;
import com.bit.sc.module.group.service.GroupRelService;
import com.bit.sc.module.group.vo.GroupRelVO;
import com.bit.sc.module.group.vo.GroupResEquVo;
import com.bit.sc.module.resident.dao.ResidentMapper;
import com.bit.sc.module.resident.pojo.Resident;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * GroupRel的Service实现类
 * @author codeGenerator
 *
 */
@Service("groupRelService")
public class GroupRelServiceImpl extends BaseService implements GroupRelService {

    private static final Logger logger = LoggerFactory.getLogger(GroupRelServiceImpl.class);

    @Autowired
    private GroupRelMapper groupRelMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;
    /**
     * 根据条件查询GroupRel
     * @param
     * @return
     */
    @Override
    public BaseVo findByConditionPage(GroupRelVO groupRelVO){
        PageHelper.startPage(groupRelVO.getPageNum(), groupRelVO.getPageSize());
        List<GroupRel> list = groupRelMapper.findByConditionPage(groupRelVO);
        PageInfo<GroupRel> pageInfo = new PageInfo<GroupRel>(list);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(pageInfo);
        return baseVo;
    }
    /**
     * 根据条件查询GroupRel  java处理relName  ====》人和设备
     * @param
     * @return
     */
    @Override
    public BaseVo findListPage(GroupRelVO groupRelVO){
        PageHelper.startPage(groupRelVO.getPageNum(), groupRelVO.getPageSize());
        List<GroupRel> list = groupRelMapper.findListPage(groupRelVO);
        if (list!=null){
            for (GroupRel groupRel : list) {
                //这是居民
                if(groupRel.getType()==Const.GROUP_REL_RESIDENT_TYPE){
                    Resident resident = residentMapper.findById(groupRel.getRelId());
                    groupRel.setRelName(resident.getRealName());
                }
                //设备
                if (groupRel.getType()==Const.GROUP_REL_EQUIPMENT_TYPE){
                    Equipment equipment = equipmentMapper.findById(groupRel.getRelId());
                    groupRel.setRelName(equipment.getEquipmentName());
                }
            }
        }
        PageInfo<GroupRel> pageInfo = new PageInfo<GroupRel>(list);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(pageInfo);
        return baseVo;
    }
    /**
     * 查询所有GroupRel
     * @param sorter 排序字符串
     * @return
     */
    @Override
    public List<GroupRel> findAll(String sorter){
        return groupRelMapper.findAll(sorter);
    }
    /**
     * 通过主键查询单个GroupRel
     * @param id
     * @return
     */
    @Override
    public GroupRel findById(Long id){
        return groupRelMapper.findById(id);
    }

    /**
     * 保存GroupRel
     * @param groupRel
     */
    @Override
    @Transactional
    public void add(GroupRel groupRel){
        groupRelMapper.add(groupRel);
    }

    /**
     * 批量保存
     * @param groupRels
     */
    @Override
    @Transactional
    public void batchAdd(List<GroupRel> groupRels) {
        groupRelMapper.batchAdd(groupRels);
    }

    /**
     * 更新GroupRel
     * @param groupRel
     */
    @Override
    @Transactional
    public void update(GroupRel groupRel){
        groupRelMapper.update(groupRel);
    }

    /**
     * @param groupId
     * @return
     */
    @Override
    public List<GroupRel> findGroupRelList(Long groupId) {
        return groupRelMapper.findGroupRelList(groupId);
    }

    /**
     * 删除  根据groupid 和relid  和type  批量删除
     */
    @Override
    @Transactional
    public void delByParam(List<GroupRel> groupRels) {
        groupRelMapper.delByParam(groupRels);
    }

    /**
     * 根据参数  查询list
     * @param groupRel
     * @return
     */
    @Override
    public List<GroupRel> findByParam(GroupRel groupRel) {
        return groupRelMapper.findByParam(groupRel);
    }

    @Override
    public BaseVo findPageByParam(GroupResEquVo groupResEquVo) {
        PageHelper.startPage(groupResEquVo.getPageNum(), groupResEquVo.getPageSize());
        List<GroupResEqu> list=null;
        //设备
        if (groupResEquVo.getType()==Const.GROUP_REL_EQUIPMENT_TYPE){
            list= groupRelMapper.findPageEquByParam(groupResEquVo);
        }
        //居民
        if (groupResEquVo.getType()==Const.GROUP_REL_RESIDENT_TYPE){
            list = groupRelMapper.findPageResByParam(groupResEquVo);
        }
        PageInfo<GroupResEqu> pageInfo = new PageInfo<GroupResEqu>(list);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(pageInfo);
        return baseVo;

    }

    /**
     * 删除GroupRel
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id){
        groupRelMapper.delete(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional
    public void batchDelete(List<Long> ids) {
        groupRelMapper.batchDelete(ids);
    }

}
