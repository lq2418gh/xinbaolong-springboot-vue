package com.bit.sc.module.sys.dao;

import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.vo.AddressVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Address管理的Dao
 * @author liqi
 *
 */
public interface AddressMapper {
	/**
	 * 根据条件查询Address--小区
	 * @param addressVO
	 * @return
	 */
	public List<Address> findByConditionPage(AddressVO addressVO);
	/**
	 * 查询所有Address
	 * @return
	 */
	public List<Address> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Address
	 * @param id	 	 
	 * @return
	 */
	public Address findById(@Param(value = "id") Long id);
	/**
	 * 保存Address
	 * @param addressVO
	 */
	public Long add(Address addressVO);
	/**
	 * 更新Address
	 * @param addressVO
	 */
	public void update(Address addressVO);
	/**
	 * 批量删除Address
	 * @param ids
	 */
	public void batchDelete(@Param(value = "ids")List<Long> ids);
	/**
	 * 删除Address
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 根据条件查询Address
	 * @param address
	 * @return
	 */
    public List<Address> findAddressListByParam(Address address);

	/**
	 * 根据fid 查询父节点adderss 对象
 	 * @param fid
	 * @return
	 */
	Address findByFid(@Param(value="fid")Long fid);

	/**
	 * 查询地址---单节点查询树
	 * @param fid
	 * @return
	 */
	List<Address> findListByFid(@Param(value="fid")Long fid);

	/**
	 * 根据valuecode 查询统计  校验唯一
	 * @param valueCode
	 * @return
	 */
    int findCountByValueCode(Integer valueCode);

	void deleteByAddressCode(String addressCode);

	/**
	 * 根据addressCode查询子集
	 * @param address
	 * @return
	 */
	List<Address> findAllByAddressCode(Address address);

	/**
	 * 根据表id查询对象
	 * @param id
	 * @return
	 */
    Address findByPrimaryKey(@Param(value = "id") Long id);
	/**
	 * 启动或者停止
	 * @param address
	 */
	void updateStartOrStop(Address address);

	/**
	 * 查询小区到大门的数据
	 * @return
	 */
    List<Address> findToDoorList();
	/**
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
	 * 根据addresscode查询对象
	 * @param addressCode
	 * @return
	 */
	Address findByAddressCode(String addressCode);

	/**
	 * 根据地址编码模糊查询地址list
	 * @param addressCode
	 * @return
	 */
	List<Address> findAddListLikeCode(String addressCode);

	/**
	 * 只查询小区
	 * @return
	 */
    List<Address> findVillage();

	/**
	 * 根据用户  当前的地址 查询到门的地址
	 * @param addressCode
	 * @return
	 */
	List<Address> findToDoorByUser(String addressCode);
	/**
	 * 根据用户  当前的地址 查询到单元的地址
	 * @param addressCode
	 * @return
	 */
	List<Address> findToResidentByUser(String addressCode);

	/**
	 * 批量更新addressCode
	 * @param addresses
	 */
	void updateAddressCodeBatch(List<Address> addresses);

	/**
	 * 查询杨柳青 -- 根基节点
	 * @param areaCode
	 * @return
	 */
	Address findByAreaCode(String areaCode);
}
