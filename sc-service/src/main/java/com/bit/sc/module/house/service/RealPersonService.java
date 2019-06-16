package com.bit.sc.module.house.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.house.pojo.RealPerson;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * RealPerson的Service
 * @author chenduo
 */
public interface RealPersonService {



	/**
	 * 保存RealPerson
	 * @param realPerson
	 *         @author chenduo
	 */
	BaseVo add(RealPerson realPerson);

	/**
	 * 更新RealPerson
	 * @param realPerson
	 * @author chenduo
	 */
	void update(RealPerson realPerson);
	/**
	 * 删除RealPerson
	 * @param id
	 * @author chenduo
	 */
	void delete(Long id);


	/**
	 *
	 * 功能描述:上传照片
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/27 13:39
	 */
	BaseVo uploadpicture(MultipartFile file);

    /**
     *
     * 功能描述:根据id查询记录 用于反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 15:38
     */
	BaseVo queryData(Long id);
	/**
	 *
	 * 功能描述:根据houseid查询结果
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/3 11:55
	 */
	List<RealPerson> findByHouseId(Long id);
}
