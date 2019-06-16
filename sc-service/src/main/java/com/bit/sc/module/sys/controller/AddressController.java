package com.bit.sc.module.sys.controller;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.attachment.vo.AttachmentThirdVO;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import com.bit.sc.module.sys.service.AddressCommonService;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.module.sys.service.ResidentRelAddressService;
import com.bit.sc.module.sys.vo.AddressVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Address的相关请求
 * @author liqi
 */
@RestController
@RequestMapping(value = "/address")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ResidentRelAddressService residentRelAddressService;
    @Autowired
    private AddressCommonService addressCommonService;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 分页查询Address列表  小区 的分页查询  当中查询了图片（）
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody AddressVO addressVO) {
        //分页对象，前台传递的包含查询的参数
        BaseVo byConditionPage = addressService.findByConditionPage(addressVO);
        return byConditionPage;
    }

    /**
     * 保存小区
     * @param address
     * @return
     */
    @PostMapping("/villageAdd")
    public BaseVo villageAdd(@RequestBody Address address) {
        addressCommonService.villageAdd(address);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 保存房屋
     * @param address
     * @return
     */
    @PostMapping("/roomAdd")
    public BaseVo roomAdd(@RequestBody Address address) {
        addressService.roomAdd(address);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 根据主键ID查询Address 和区域编码  联查   现在此方法只用于只限小区的（需要 请看service层和dao）
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {
        Address address =addressService.findById(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(address);
        return baseVo;
    }
    /**
     * 根据主键ID查询Address  只查询address表的对象
     * @param id
     * @return
     */
    @GetMapping("/findByPrimaryKey/{id}")
    public BaseVo findByPrimaryKey(@PathVariable(value = "id") Long id) {
        Address address =addressService.findByPrimaryKey(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(address);
        return baseVo;
    }
    /**
     * 查询地址对象和区域的名字
     * @param id
     * @return
     */
    @GetMapping("/findAddressAndAreaName/{id}")
    public BaseVo findAddressAndAreaName(@PathVariable(value = "id") Long id){
        Address address =addressService.findAddressAndArea(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(address);
        return baseVo;
    }
    /**
     * 根据小区的id查询小区对象和下级目录
     * @param id   小区的id
     * @return
     */
    @GetMapping("/findByVillageId/{id}")
    public BaseVo findByVillageId(@PathVariable(value = "id") Long id){
        Address address=addressService.findByVillageId(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(address);
        return baseVo;
    }
    /**
     * 修改小区
     * @param address
     * @return
     */
    @PostMapping("/villageModify")
    public BaseVo villageModify(@Valid @RequestBody Address address) {
        addressCommonService.villageModify(address);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 保存房屋
     * @param address
     * @return
     */
    @PostMapping("/roomModify")
    public BaseVo roomModify(@Valid @RequestBody Address address) {
        addressService.roomModify(address);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 根据主键ID删除Address  包括子集节点list全部删除   和  居民地址中间表关联的全部  和图片表
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        addressService.delete(id);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 保存方法  --residentRelAddress  居民地址中间表
     * @param residentRelAddress
     * @return
     */
    @PostMapping("/addResidentRelAddress")
    public BaseVo add(@RequestBody ResidentRelAddress residentRelAddress) {
        residentRelAddressService.add(residentRelAddress);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 查询地址----树
     */
    @PostMapping("/findAddressTree")
    public BaseVo findAddressTree() {
        List<Address> addressTree = addressService.findAddressTree();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(addressTree);
        return baseVo;
    }
    /**
     * 查询地址---单节点查询树
     * @param fid
     * @return
     */
    @GetMapping("/findListByFid/{fid}")
    public BaseVo findListByFid(@PathVariable(value = "fid") Long fid) {
        List<Address> addressTree =addressService.findListByFid(fid);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(addressTree);
        return baseVo;
    }
    /**
     * valueCode 查询统计
     * @param valueCode
     * @return
     */
    @GetMapping("/findCountByValueCode/{valueCode}")
    public BaseVo findCountByValueCode(@PathVariable(value = "valueCode") Integer valueCode) {
        int findCountByAddress = addressService.findCountByValueCode(valueCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(findCountByAddress);
        return baseVo;
    }

    /**
     * 同步缓存
     */
    @PostMapping("/synchronizationCache")
    public BaseVo synchronizationCache() {
        return addressService.synchronizationCache();
    }


    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public  BaseVo uploadFiles(@RequestParam(value="file")MultipartFile file){
    	Attachment attachment = new Attachment();
    	attachment.setBusinessId(ModuleFileType.ADDRESS_IMAGE);

        Long addAttachment  = attachmentService.addAttachment(attachment,file);
        attachmentService.findByAttachmentId(addAttachment);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(attachment);
        return baseVo;
    }

    /**
     * 启动或者停止
     * @param id
     * @param villageState
     * @return
     */
    @GetMapping("/startOrStop/{id}/{villageState}")
    public BaseVo updateStartOrStop(@PathVariable(value = "id")Long id,@PathVariable(value = "villageState")Integer villageState) {
    	addressService.updateStartOrStop(id,villageState);
    	BaseVo baseVo = new BaseVo();
    	return  baseVo;
    }

    /**
     * 查询小区到大门的数据
     * @return
     */
    @PostMapping("/findDoorList")
    public  BaseVo findDoorList(){
        List<Address> toDoorList = addressService.findToDoorList();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(toDoorList);
        return baseVo;
    }

    /**
     * 查询小区到单元的数据
     * @return
     */
    @PostMapping("/findToResident")
    public  BaseVo findToResident(){
        List<Address> toDoorList = addressService.findToResident();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(toDoorList);
        return baseVo;
    }
    /**
     * 根据当前用户查询小区到大门的数据 --车辆授权
     * @return
     */
    @PostMapping("/findToDoorByUser")
    public  BaseVo findToDoorByUser(){
        List<Address> toDoorList = addressService.findToDoorByUser();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(toDoorList);
        return baseVo;
    }

    /**
     * 根据当前用户查询小区到单元的数据--居民授权
     * @return
     */
    @PostMapping("/findToResidentByUser")
    public  BaseVo findToResidentByUser(){
        List<Address> toDoorList = addressService.findToResidentByUser();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(toDoorList);
        return baseVo;
    }
    /**
     * 根据用户查询地址树
     * @return
     */
    @PostMapping("/findAddressByUser")
    public  BaseVo findAddressByUser(){
        List<Address> addressTree = addressService.findAddressTreeByUser();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(addressTree);
        return baseVo;
    }
    /**
     * 查询所有小区
     * @return
     */
    @PostMapping("/findVillage")
    public  BaseVo findVillage(){
        List<Address> addressList = addressService.findVillage();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(addressList);
        return baseVo;
    }

    /**
     * 查询所有杨柳青和小区
     * @return
     */
    @PostMapping("/findTownVillage")
    public  BaseVo findTownVillage(){
        List<Address> addressList = addressService.findTownVillage();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(addressList);
        return baseVo;
    }

    @GetMapping("/checkOrgName/{orgName}")
    public void checkOrgName(@PathVariable(value = "orgName") String orgName){
        Address adr = new Address();
        adr.setAddressName(orgName);
        List<Address> addressList = addressMapper.findAddressListByParam(adr);
        if (addressList.size()>0){
            throw new BusinessException("该小区名称已存在");
        }
    }
}
