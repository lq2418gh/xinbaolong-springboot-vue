package com.bit.sc.module.house.service.impl;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.module.house.dao.RegisteredResidentMapper;
import com.bit.sc.module.house.pojo.RegisteredResident;
import com.bit.sc.module.house.service.RegisteredResidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RegisteredResident的Service实现类
 * @author chenduo
 *
 */
@Service("registeredResidentService")
public class RegisteredResidentServiceImpl extends BaseService implements RegisteredResidentService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisteredResidentServiceImpl.class);
	
	@Autowired
	private RegisteredResidentMapper registeredResidentMapper;
	

	/**
	 * 通过主键查询单个RegisteredResident
	 * @param id
	 * @return
	 */
	@Override
	public RegisteredResident findById(Long id){
		return registeredResidentMapper.findById(id);
	}
	

	/**
	 * 保存RegisteredResident
	 * @param registeredResident
	 */
	@Override
	@Transactional
	public BaseVo add(RegisteredResident registeredResident){
		registeredResidentMapper.add(registeredResident);
        RegisteredResident temp = registeredResidentMapper.findById(registeredResident.getId());
		BaseVo baseVo = new BaseVo();
		baseVo.setData(temp);
		return baseVo;
	}

	/**
	 * 更新RegisteredResident
	 * @param registeredResident
	 */
	@Override
	@Transactional
	public void update(RegisteredResident registeredResident){
		registeredResidentMapper.update(registeredResident);
	}




	/**
	 * 删除RegisteredResident
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		registeredResidentMapper.delete(id);
	}


	/**
	 *
	 * 功能描述:根据houseid查询结果
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/3 11:55
	 */
    @Override
    public List<RegisteredResident> findByHouseId(Long id) {

        return registeredResidentMapper.findByHouseId(id);
    }
}
