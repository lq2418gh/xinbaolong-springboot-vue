package com.bit.sc.module.sys.service.impl;

import java.util.Date;
import java.util.List;

import com.bit.base.service.BaseService;
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
import com.bit.sc.module.sys.pojo.Function;
import com.bit.sc.module.sys.vo.FunctionVO;
import com.bit.sc.module.sys.dao.FunctionMapper;
import com.bit.sc.module.sys.service.FunctionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Function的Service实现类
 * @author liqi
 *
 */
@Service("functionService")
public class FunctionServiceImpl extends BaseService implements FunctionService{
	
	private static final Logger logger = LoggerFactory.getLogger(FunctionServiceImpl.class);
	
	@Autowired
	private FunctionMapper functionMapper;

	@Autowired
	private RoleRelPermissionMapper roleRelPermissionMapper;

	/**
	 * 根据条件查询Function
	 * @param functionVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(FunctionVO functionVO){
		BaseVo baseVo = new BaseVo();
		PageHelper.startPage(functionVO.getPageNum(), functionVO.getPageSize());
		List<Function> list = functionMapper.findByConditionPage(functionVO);
		PageInfo<Function> pageInfo = new PageInfo<Function>(list);
		baseVo.setData(pageInfo);
		return baseVo;
	}

	/**
	 * 查询所有Function
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Function> findAll(String sorter){
		return functionMapper.findAll(sorter);
	}

	/**
	 * 通过主键查询单个Function
	 * @param id
	 * @return
	 */
	@Override
	public Function findById(Long id){
		return functionMapper.findById(id);
	}
	
	/**
	 * 批量保存Function
	 * @param functions
	 */
	@Override
	@Transactional
	public void batchAdd(List<Function> functions){
		functionMapper.batchAdd(functions);
	}

	/**
	 * 保存Function
	 * @param function
	 */
	@Override
	@Transactional
	public void add(Function function){
		//todo 处理时间格式
		check(function);
		function.setCreateTime(new Date());
		function.setCreateUserId(getCurrentUserInfo().getId());
		functionMapper.add(function);
	}

	/**
	 * 批量更新Function
	 * @param functions
	 */
	@Override
	@Transactional
	public void batchUpdate(List<Function> functions){
		functionMapper.batchUpdate(functions);
	}

	/**
	 * 更新Function
	 * @param function
	 */
	@Override
	@Transactional
	public void update(Function function){
		//todo 处理时间格式
		check(function);
		function.setUpdateTime(new Date());
		function.setUpdateUserId(getCurrentUserInfo().getId());
		functionMapper.update(function);
	}

	/**
	 * 删除Function
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		functionMapper.batchDelete(ids);
	}
	/**
	 * functionCode 查询统计
	 * @param functionCode
	 * @return
	 */
	@Override
	public int findCountByFunctionCode(String functionCode) {
		return functionMapper.findCountByFunctionCode(functionCode);
	}

	/**
	 * 删除Function
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		functionMapper.delete(id);
		RoleRelPermission roleRelPermission = new RoleRelPermission();
		roleRelPermission.setFunctionId(id);
		roleRelPermission.setPermissionType(Const.PERMISSION_TYPE_APP);
		roleRelPermissionMapper.deleteByFunction(roleRelPermission);
	}

	/**
	 * 校验  字段 非空
	 */
	public void check(Function function){
		CheckUtil.notEmpty(function.getFunctionName(),"功能名称不能为空");
		CheckUtil.notEmpty(function.getFunctionCode(),"功能编码 不能为空");
		CheckUtil.notNull(function.getApplicationId(),"应用编码不能为空");
		CheckUtil.notNull(function.getIsDisplay(),"是否显示不能为空");
	}
}
