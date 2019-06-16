package com.bit.sc.module.sys.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.vo.AddressVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Address  service接口
 * @author  liqi
 */
@Service
public interface AddressService {

    /**
     * 根据条件查询Address 此方法用于   小区  的分页查询  还查询了图片表  放进地址数据中
     * @param addressVO
     * @return
     */
    BaseVo findByConditionPage(AddressVO addressVO);
    /**
     * 查询所有Address
     * @param sorter 排序字符串
     * @return
     */
    List<Address> findAll(String sorter);
    /**
     * 通过主键查询单个Address加上AreaCode 对象--现在业务上--小区在用查询对象的方法
     * @param id
     * @return
     */
    Address findById(Long id);
    /**
     * 通过主键查询单个Address  根据主键ID查询Address  只查询address表的对象
     * @param id
     * @return
     */
    Address findByPrimaryKey(Long id);
    /**
     * 删除Address
     * @param id
     */
    void delete(Long id);
    /**
     * 批量删除Address
     * @param ids
     */
    void batchDelete(List<Long> ids);

    /**
     * 根据参数查询所有  不带分页
     * @param address
     */
    List<Address> findAddressListByParam(Address address);

    /**
     * 查询address数据--树
     */
    List<Address> findAddressTree();


    Address findByFid(Long fid);
    /**
     * addressCode 查询统计
     * @param valueCode
     * @return
     */
    int findCountByValueCode(Integer valueCode);

    /**
     * 查询地址---单节点查询树
     * @param fid
     * @return
     */
    List<Address> findListByFid(Long fid);

    /**
     * 根据小区的id查询小区对象和下级目录
     * @param id   小区的id
     * @return
     */
    Address findByVillageId(Long id);

    BaseVo synchronizationCache();

    /***
     * 根据areaCode查询地址集合
     * @param parentCode
     * @return
     */
    List<Address> findByAreaCode(String parentCode);

    /**
     * 启动或者停止  更新操作
     * @param id
     */
    void updateStartOrStop(Long id,Integer villageState);

    /***
     * 查询小区到大们的数据
     * @return
     */
    List<Address> findToDoorList();
    /***
     * 查询小区到单元的数据
     * @return
     */
    List<Address> findToResident();
    /**
     * 查询地址对象和区域的名字
     * @param id
     * @return
     */
    Address findAddressAndArea(Long id);
    /**
     * 根据addressCode查询对象
     * @param addressCode
     * @return
     */
    Address findByAddressCode(String addressCode);

    /**
     * 通过  当前  用户  查询  当前地址树
     * @return
     */
    List<Address> findAddressTreeByUser();
    /**
     * 查询所有小区
     * @return
     */
    List<Address> findVillage();
    /**
     * 根据当前用户查询小区到大门的数据
     * @return
     */
    List<Address> findToDoorByUser();
    /**
     * 根据当前用户查询小区到单元的数据
     * @return
     */
    List<Address> findToResidentByUser();
    /**
     * 查询镇河小区
     * @return
     */
    List<Address> findTownVillage();
    /**
     * 保存小区
     * @param address
     */
    void villageAdd(Address address);
    /**
     * 保存房屋
     * @param address
     */
    void roomAdd(Address address);
    /**
     * 修改小区
     * @param address
     */
    void villageModify(Address address);
    /**
     * 修改房屋
     * @param address
     */
    void roomModify(Address address);

    /**
     * 根据小区子集code获取顶级地址对象
     * @param addressCode
     * @return
     */
    Address findTopBySubset(String addressCode);
}
