package com.bit.sc.module.resident.controller;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BasePageVo;
import com.bit.base.vo.BaseVo;
import com.bit.common.ResultCode;
import com.bit.sc.common.Const;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.resident.pojo.Resident;
import com.bit.sc.module.resident.service.ResidentCommonService;
import com.bit.sc.module.resident.service.ResidentService;
import com.bit.sc.module.resident.vo.ResidentBatchAuthorizeVO;
import com.bit.sc.module.resident.vo.ResidentMobileVO;
import com.bit.sc.module.resident.vo.ResidentVO;
import com.bit.utils.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 居民的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/resident")
public class ResidentController {

	private static final Logger logger = LoggerFactory.getLogger(ResidentController.class);

	@Autowired
	private ResidentService residentService;
	@Autowired
    private ResidentCommonService residentCommonService;

	@Autowired
	private CacheUtil cacheUtil;

	@PostMapping("/login")
	public BaseVo login(@RequestBody ResidentVO residentVO) {
        BaseVo baseVo = residentService.login(residentVO);
        return baseVo;
	}

	@PostMapping("/smsCode")
	public BaseVo getSmsCode(@RequestBody ResidentVO residentVO) {
        BaseVo baseVo = residentService.getSmsCode(residentVO);
		return baseVo;
	}

	/**
	 * app用户注册业务
	 * @param  residentMobileVO
	 * @return baseVo
	 * @author zhangjie
	 * @date 2018-11-13
	 */
	@PostMapping("/appRegister")
	public BaseVo appRegister(@RequestBody ResidentMobileVO residentMobileVO){
		BaseVo baseVo = residentService.appRegister(residentMobileVO);
		return baseVo;
	}

	/**
	 * app用户登录业务
	 * @param  residentMobileVO
	 * @return baseVo
	 * @author zhangjie
	 * @date 2018-11-13
	 */
	@PostMapping("/appLogin")
	public BaseVo appLogin(@RequestBody ResidentMobileVO residentMobileVO){
		BaseVo baseVo = residentService.appLogin(residentMobileVO);
		return baseVo;
	}

	/**
	 * app用户注销登录业务
	 * @param  residentMobileVO
	 * @return baseVo
	 * @author zhangjie
	 * @date 2018-11-14
	 */
	@PostMapping("/logout")
	public BaseVo logout(@RequestBody ResidentMobileVO residentMobileVO){
		BaseVo baseVo = null;
		try {
			baseVo = residentService.logout(residentMobileVO);

		}catch (Exception e){
			throw  new BusinessException(e);
		}
		return baseVo;
	}

	/**
	 * app修改密码业务
	 * @param  residentMobileVO
	 * @return baseVo
	 * @author zhangjie
	 * @date 2018-11-14
	 */
	@PostMapping("/forgetPassword")
	public BaseVo forgetPassword(@RequestBody ResidentMobileVO residentMobileVO){
		BaseVo baseVo = residentService.forgetPassword(residentMobileVO);
		return baseVo;
	}

	/**
	 * app发送验证码业务
	 * @param  residentMobileVO
	 * @return baseVo
	 * @author zhangjie
	 * @date 2018-11-20
	 */
	@PostMapping("/sendCode")
	public BaseVo sendVaildCode(@RequestBody ResidentMobileVO residentMobileVO){

		if (residentMobileVO.getPhone() == null) {
			throw new BusinessException("请输入手机号");
		}else {
			residentService.sendVaildCode(residentMobileVO);
		}
		return new BaseVo();
	}

	/**
	 * 查询所有居民
	 *
	 */
	@GetMapping("/findAll")
	public BaseVo findAll(String sorter){
		sorter = "id desc";
		List<Resident> residents = residentService.findAll(sorter);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(residents);
		return baseVo;
	}

    /**
     * 新增Resident
     *
     * @param residentVO Resident实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add( @RequestBody ResidentVO residentVO) {
		residentCommonService.add(residentVO);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 修改Resident
     *
     * @param residentVO
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@RequestBody ResidentVO residentVO) {
		residentCommonService.modify(residentVO);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据主键ID删除Resident
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
		residentCommonService.delete(id);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     *
     * 功能描述:上传照片
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:39
     */

    @PostMapping("/uploadpicture")
    public BaseVo uploadPicture(@RequestParam("file") MultipartFile file){

		return residentCommonService.uploadpicture(file);
    }

    /**
     * 联合居民地址表 地址表 居民手机表 附件表
     *
     * @param
     * @return
     * @author chenduo
     */
    @PostMapping("/queryResidentInfoByPage")
    public BaseVo queryListDictByPage(@RequestBody ResidentVO residentVO) {

        return residentService.queryResidentInfoByPage(residentVO);
    }
    /**
     *
     * 功能描述:授权人脸白名单
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 9:42
     */

    @PostMapping("/authorize")
    public BaseVo authorize(@RequestBody ResidentVO residentVO){
        return residentCommonService.authorize(residentVO);
    }

    /**
     *
     * 功能描述:编辑的时候反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/29 9:04
     */

    @GetMapping("/reflect/{id}")
	public BaseVo reflect(@PathVariable(value = "id") Long id){
    	return residentCommonService.reflect(id);
//        return residentService.reflect(id);
    }


	/**
	 *
	 * 功能描述:批量授权
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/12 16:36
	 */

	@PostMapping("/batchAuthorize")
	public BaseVo batchAuthorize(@RequestBody ResidentBatchAuthorizeVO residentBatchAuthorizeVO){
		return residentCommonService.batchAuthorize(residentBatchAuthorizeVO);
	}

	/**
	 *
	 * 功能描述:授权人脸白名单
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/28 9:42
	 */

	@PostMapping("/revoke")
	public BaseVo revoke(@RequestBody ResidentVO residentVO){
		return residentCommonService.revoke(residentVO);
	}

    /**
     *
     * 功能描述:批量取消授权
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/12 16:36
     */
    @PostMapping("/batchRevoke")
    public BaseVo batchRevoke(@RequestBody ResidentBatchAuthorizeVO residentBatchAuthorizeVO){
        return residentCommonService.batchRevoke(residentBatchAuthorizeVO);
    }
	/**
	 *
	 * 功能描述:批量导入
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/12 16:36
	 */

	@PostMapping("/batchImport")
	public BaseVo batchImport(){
		return null;
	}
	/**
	 *
	 * 功能描述:授权反显
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/14 14:13
	 */

	@GetMapping("/authorizationReflect/{id}")
	public BaseVo authorizationReflect(@PathVariable(value = "id") Long id){
		return residentCommonService.authorizationReflect(id);
	}

}
