package com.bit.sc.module.sys.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.dao.AttachmentMapper;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.sys.dao.AddressContactMapper;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.dao.AreaCodeMapper;
import com.bit.sc.module.sys.dao.ResidentRelAddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.AddressContact;
import com.bit.sc.module.sys.pojo.AreaCode;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.module.sys.vo.AddressVO;
import com.bit.sc.utils.AddressUtil;
import com.bit.sc.utils.CheckUtil;
import com.bit.utils.CacheUtil;
import com.bit.utils.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Address的Service实现类
 * @author liqi
 *
 */
@Service("addressService")
public class AddressServiceImpl extends BaseService implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private ResidentRelAddressMapper residentRelAddressMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private AreaCodeMapper areaCodeMapper;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private AddressContactMapper addressContactMapper;
    /**
     * 根据条件查询Address 小区的分页查询  查询了图片表  放进地址数据中 拼装分页数据返回
     * @param addressVO
     * @return
     */
    @Override
    public BaseVo findByConditionPage(AddressVO addressVO){
        BaseVo baseVo = new BaseVo();
        PageHelper.startPage(addressVO.getPageNum(), addressVO.getPageSize());
        //只查询小区的---分页 参数fid为0
//        addressVO.setFid(Const.ROOT_ADDRESS_FID);
        addressVO.setAddressLevel(Const.ROOT_ADDRESS_LEVEL);
        List<Address> list = addressMapper.findByConditionPage(addressVO);
        if (list!=null){
            //查询出图片是地址模块下的全部  对比放入  把地址模块下对应的图片放进 address对象中  封装数据  返回
            //todo 后续需要修改此条查询   数量量过大  存在性能问题
            List<Attachment> findFileList = this.findFileByAddress(ModuleFileType.ADDRESS_IMAGE);
            if (findFileList!=null) {
                for (Address address : list) {
                    List<Attachment> arrayList = new ArrayList<Attachment>();
                    for (Attachment attachment : findFileList) {
                        if (address.getId().equals(attachment.getDataId())) {
                            arrayList.add(attachment);
                        }
                    }
                    address.setFileList(arrayList);
                }
            }
        }
        PageInfo<Address> pageInfo = new PageInfo<Address>(list);
        baseVo.setData(pageInfo);
        return baseVo;
    }
    /**
     * 根据业务类型查询文件表 （查询文件表 此模块的图片所有）
     * @param businessId
     * @return
     */
    public List<Attachment> findFileByAddress(int businessId) {
		Attachment attachment = new Attachment();
		attachment.setBusinessId(businessId);
    	List<Attachment> findAllByParams = attachmentMapper.findAllByParams(attachment);
    	return findAllByParams;
	}
    /**
     * 查询所有address
     * @param sorter 排序字符串
     * @return
     */
    @Override
    public List<Address> findAll(String sorter){
        return addressMapper.findAll(sorter);
    }
    /**
     * 通过主键查询单个address 和AreaCode 对象联查--此方法现在用于小区的查询对象
     * @param id
     * @return
     */
    @Override
    public Address  findById(Long id){
        Address byId = addressMapper.findById(id);
        //如果是小区点查询
        if (byId.getAddressLevel()==Const.ROOT_ADDRESS_LEVEL){
            String codeName = this.appendAreaCode(byId.getAreaCode());
            byId.setArName(codeName);
            List<Attachment> allByParams = this.findAttachmentByParam(id);
            byId.setFileList(allByParams);

            AddressContact addressContact = new AddressContact();
            addressContact = addressContactMapper.findById(id);
            byId.setAddressContact(addressContact);
        }
        return byId;
    }

    /**
     * 根据  业务id 和业务类型 查询图片表
     * @param id
     * @return
     */
    public List<Attachment> findAttachmentByParam(Long id){
        Attachment attachment=new Attachment();
        attachment.setDataId(id);
        attachment.setBusinessId(ModuleFileType.ADDRESS_IMAGE);
        return attachmentMapper.findAllByParams(attachment);
    }
    /**
     * 根据Code查询AreaCode表拼装Name --（类如：天津市，杨柳青，居委会）
     * @param code
     */
    public String appendAreaCode(String code){
        String areaCode=code;
        AreaCode byArCode = areaCodeMapper.findByArCode(code);
        if (byArCode!=null){
            AreaCode byArCode1 = areaCodeMapper.findByArCode(byArCode.getParentCode());
            if (byArCode1!=null){
                areaCode=byArCode1.getArName()+byArCode.getArName();
            }
        }
        return areaCode;
    }

    /**
     * 查询 address对象 单条
     * @param id
     * @return
     */
    @Override
    public Address findByPrimaryKey(Long id){
        return  addressMapper.findByPrimaryKey(id);
    }

    /**
     * 根据file
     * @param id （attachment） 图片表的id
     * @param dataId 图片表的dataId  业务id
     */
    public void updateDataId(Long id, Long dataId) {
        Attachment attachment = new Attachment();
        attachment.setAttachmentId(id);
        attachment.setDataId(dataId);
        attachmentMapper.updateDataId(attachment);
    }

    public String getAddressDetail(String addressDetail,int addressLevel){
        String[] split = addressDetail.split(",");
        String code="";
        for (int i = addressLevel; i < split.length; i++) {
            code+=","+split[i];
        }
        return  code;
    }
    /**
     * 删除Address
     * @param ids
     */
    @Override
    @Transactional
    public void batchDelete(List<Long> ids){
        addressMapper.batchDelete(ids);
    }

    /**
     * 根据条件 查询 List
     * @param address
     * @return
     */
    @Override
    public List<Address> findAddressListByParam(Address address) {
        return addressMapper.findAddressListByParam(address);
    }

    /**
     * 查询--树
     * @return
     */
    @Override
    public List<Address> findAddressTree() {
        List<Address> addressList = addressMapper.findAddressListByParam(null);
        List<Address> rootList = new ArrayList<>();
        for (Address address : addressList) {
            if (address.getFid()==Const.ROOT_ADDRESS_FID){
                rootList.add(address);
            }
        }
        for (Address address : rootList) {
            address.setChildrenList(getChild(address.getId(), addressList));
        }
        return  rootList;
    }

    /**
     * 根据fid 查询address对象
     * @param fid
     * @return
     */
    @Override
    public Address findByFid(Long fid) {
        return addressMapper.findByFid(fid);
    }

    /**
     * valueCode 查询统计  校验code唯一
     * @param valueCode
     * @return
     */
    @Override
    public int findCountByValueCode(Integer valueCode) {
        return addressMapper.findCountByValueCode(valueCode);
    }
    /**
     * 查询地址---单节点查询树 ()
     * @param fid
     * @return
     */
    @Override
    public List<Address> findListByFid(Long fid) {
        return addressMapper.findListByFid(fid);
    }

    /**
     * 根据小区id 查询对象和下级集合
     * @param id
     * @return
     */
    @Override
    public Address findByVillageId(Long id) {
        Address byId = addressMapper.findAddressAndArea(id);
        List<Address> listByFid = addressMapper.findListByFid(byId.getId());
        if (listByFid.size()>0){
            byId.setChildrenList(listByFid);
        }
        return byId;
    }


    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param addressList
     *            菜单数据
     * @return
     */
    private List<Address> getChild(Long id, List<Address> addressList) {
        // 子菜单
        List<Address> childList = new ArrayList<>();
        for (Address address : addressList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (address.getFid()!=null) {
                if (address.getFid().equals(id)) {
                    childList.add(address);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Address address : childList) {// 没有url子菜单还有子菜单
                // 递归
            address.setChildrenList(getChild(address.getId(), addressList));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 删除Address --如果是父级节点 那就删除子集节点所有  删除中间表的所有
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id){
        Address byId = addressMapper.findByPrimaryKey(id);
        if (byId != null){
            residentRelAddressMapper.deleteByLikeAddressCode(byId.getAddressCode());
            //是小区的话需要删除图片  如果不是就不用了
            if (byId.getAddressLevel()==Const.ROOT_ADDRESS_LEVEL){
                List<Attachment> allByParams =this.findAttachmentByParam(id);
                if (allByParams!=null){
                    List<Long> ids = new ArrayList<>();
                    for (Attachment obj : allByParams) {
                        ids.add(obj.getAttachmentId());
                    }
                    if (ids.size()>0){
                        attachmentMapper.batchDelete(ids);//批量删除图片
                    }
                }
            }
            addressMapper.deleteByAddressCode(byId.getAddressCode());
        }

    }
    /**
     * 将地址code同步到redis中
     */
    @Override
    public BaseVo synchronizationCache() {
        List<Address> list = addressMapper.findAll(null);
        for (Address address : list) {
            cacheUtil.set(Const.REDIS_KEY_ADDRESS + address.getAddressCode(), JSONUtil.toJSONString(address));
        }
        return new BaseVo();
    }
    /***
     * 根据areaCode查询地址集合
     * @param parentCode
     * @return
     */
    @Override
    public List<Address> findByAreaCode(String parentCode) {
        List<Address> addressList = addressMapper.findAddressListByParam(null);
        List<Address> rootList = new ArrayList<>();
        for (Address address : addressList) {
            if (address.getAreaCode().equals(parentCode)){
                rootList.add(address);
            }
        }
        for (Address address : rootList) {
            address.setChildrenList(getChild(address.getId(), addressList));
        }
        return  rootList;
    }

    /**
     * 查询小区到大门的数据
     * @return
     */
    @Override
    public List<Address> findToDoorList(){
        List<Address> toDoorList = addressMapper.findToDoorList();
        List<Address> rootList = new ArrayList<>();
        for (Address address : toDoorList) {
            if (address.getAddressLevel()==Const.ROOT_ADDRESS_LEVEL){
                rootList.add(address);
            }
        }
        for (Address address : rootList) {
            address.setChildrenList(getChild(address.getId(), toDoorList));
        }
        return  rootList;
    }

    /**
     * 查询小区到单元的数据
     * @return
     */
    @Override
    public List<Address> findToResident() {
        List<Address> toResidentList = addressMapper.findToResident();
        List<Address> rootList = new ArrayList<>();
        for (Address address : toResidentList) {
            if (address.getAddressLevel()==Const.ROOT_ADDRESS_LEVEL){
                rootList.add(address);
            }
        }
        for (Address address : rootList) {
            address.setChildrenList(getChild(address.getId(), toResidentList));
        }
        return  rootList;
    }
    /**
     * 查询地址对象和区域的名字
     * @param id
     * @return
     */
    @Override
    public Address findAddressAndArea(Long id) {
        return addressMapper.findAddressAndArea(id);
    }

    /**
     * 根据addresscode 查询对象
     * @param addressCode
     * @return
     */
    @Override
    public Address findByAddressCode(String addressCode) {
        return addressMapper.findByAddressCode(addressCode);
    }
    /**
     * 通过  当前  用户  查询  当前地址树
     * @return
     */
    @Override
    public List<Address> findAddressTreeByUser() {
        UserAddress currentUserAddresses = getCurrentUserInfo().getCurrentUserAddresses();

        Long id = getCurrentUserInfo().getId();
        String addressCode=null;
        List<Address> addressList=null;
        //todo 没有的话 默认全部权限（admin）
        if (currentUserAddresses!=null){
            if (!id.equals(Const.ADMIN_ID)){
                addressCode=currentUserAddresses.getAddressCode();
            }
            //查询登陆人的地址权限
            addressList = addressMapper.findAddListLikeCode(addressCode);
            List<Address> rootList = new ArrayList<>();
            for (Address address : addressList) {
                if (address.getAddressLevel()==Const.ROOT_ADDRESS_LEVEL){
                    rootList.add(address);
                }
            }
            for (Address address : rootList) {
                address.setChildrenList(getChild(address.getId(), addressList));
            }
            return  rootList;
        }else {
            return null;
        }
    }

    /**
     * 查询小区list
     * @return
     */
    @Override
    public List<Address> findVillage() {
        return addressMapper.findVillage();
    }
    /**
     * 根据当前用户查询小区到大门的数据
     * @return
     */
    @Override
    public List<Address> findToDoorByUser() {
        UserAddress currentUserAddresses = getCurrentUserInfo().getCurrentUserAddresses();
        List<Address> toDoorAddressList=addressMapper.findToDoorByUser(currentUserAddresses.getAddressCode());
        List<Address> rootList = new ArrayList<>();
        for (Address address : toDoorAddressList) {
            if (address.getAddressLevel()==Const.ROOT_ADDRESS_LEVEL){
                rootList.add(address);
            }
        }
        for (Address address : rootList) {
            address.setChildrenList(getChild(address.getId(), toDoorAddressList));
        }
        return  rootList;
    }
    /**
     * 根据当前用户查询小区到单元的数据
     * @return
     */
    @Override
    public List<Address> findToResidentByUser() {
        UserAddress currentUserAddresses = getCurrentUserInfo().getCurrentUserAddresses();
        List<Address> findToResidentByUser=addressMapper.findToResidentByUser(currentUserAddresses.getAddressCode());
        List<Address> rootList = new ArrayList<>();
        for (Address address : findToResidentByUser) {
            if (address.getAddressLevel()==Const.ROOT_ADDRESS_LEVEL){
                rootList.add(address);
            }
        }
        for (Address address : rootList) {
            address.setChildrenList(getChild(address.getId(), findToResidentByUser));
        }
        return  rootList;
    }

    /**
     * 查询杨柳青镇和小区
     * @return
     */
    @Override
    public List<Address> findTownVillage() {
        List<Address> listByFid = addressMapper.findListByFid(Const.ROOT_ADDRESS_FID);
        for (Address address:listByFid ) {
            List<Address> listByFid1 = addressMapper.findListByFid(address.getId());
            address.setChildrenList(listByFid1);
        }
        return listByFid;
    }

    /**
     * 保存小区
     * @param address
     */
    @Override
    @Transactional
    public void villageAdd(Address address) {
        CheckUtil.notNull(address.getAddressName(),"地址名称不能为空");
        //状态 小区的  启动停止
        address.setVillageState(Const.VILLAGE_STATE);
        //创建时间
        address.setCreateTime(new Date());
        //todo  fid是杨柳青的 Const.YANG_LIU_QING_ID  如果业务往后有别的镇   此方法修改  需要前端传fid
        address.setFid(Const.YANG_LIU_QING_ID);
        Address byFid = addressMapper.findByAreaCode(Const.AREA_CODE_ADDRESS);
        //addressDetail
        String addressDetail=byFid.getAddressDetail()+","+address.getAddressName()+","+address.getStreet()+","+address.getPlate();
        //最后更新address 拼接 父code
        String pAddressCode=byFid.getAddressCode();
        //等级
        address.setAddressLevel(Const.ROOT_ADDRESS_LEVEL);
        address.setAddressDetail(addressDetail);
        addressMapper.add(address);


        AddressContact addressContact = address.getAddressContact();
        if (addressContact!=null){
            addressContact.setAddressId(address.getId());
            addressContactMapper.addressContactAdd(addressContact);
        }



        //1 完成保存之后返回了id
        //2 查询刚才保存的对象，
        //3 循环更新文件（图片）表的dataId字段确保是地址模块下此对象的图片，
        //4  最后拼接code  更新此对象的获得最终的address对象.
        Address obj = addressMapper.findByPrimaryKey(address.getId());
        if (address.getFileList()!=null){
            List<Attachment> fileList = address.getFileList();
            if (fileList.size()>0){
                for (Attachment attachment : fileList) {
                    //根据文件id update 业务id  （dataId）
                    this.updateDataId(attachment.getAttachmentId(),address.getId());
                }
            }
        }
        obj.setAddressCode(pAddressCode+String.valueOf(obj.getId())+Const.ADDRESS_CODE);
        addressMapper.update(obj);
    }

    /**
     * 保存房屋
     * @param address
     */
    @Override
    @Transactional
    public void roomAdd(Address address) {
        addressMapper.add(address);
        Address obj = addressMapper.findByPrimaryKey(address.getId());
        Address byFid = addressMapper.findByFid(obj.getFid());
        //父级节点的code + 前段输入的code 为  现在的addressCode
        String code=byFid.getAddressCode()+String.valueOf(obj.getId())+Const.ADDRESS_CODE;
        //等级
        obj.setAddressLevel(byFid.getAddressLevel()+Const.ROOT_ADDRESS_LEVEL);
        //addressDetail
        obj.setAddressDetail(byFid.getAddressDetail()+","+obj.getAddressName());
        obj.setAddressCode(code);
        addressMapper.update(obj);
    }
    /**
     * 修改小区
     * @param address
     */
    @Override
    @Transactional
    public void villageModify(Address address) {
        CheckUtil.notNull(address.getId(),"id不能为空");
        CheckUtil.notNull(address.getAddressName(),"地址名称不能为空");
        CheckUtil.notNull(address.getAddressCode(),"地址编码不能为空");
        CheckUtil.notNull(address.getFid(),"fid不能为空");
        Address byFid = addressMapper.findByAreaCode(Const.AREA_CODE_ADDRESS);
        String addressDetail =byFid.getAddressDetail()+","+address.getAddressName()+","+address.getStreet()+","+address.getPlate();
        address.setAddressDetail(address.getAddressName());
        //修改图片
        if (address.getFileList()!=null){
            List<Attachment> fileList = address.getFileList();
            if (fileList.size()>0||fileList==null){
                for (Attachment attachment : fileList) {
                    //根据文件id update 业务id  （dataId）
                    this.updateDataId(attachment.getAttachmentId(),address.getId());
                }
            }
        }
        //如果图片操作是新增就更新图片的dataid
        List<Attachment> fileList = address.getFileList();
        if (fileList!=null){
            for (Attachment att : fileList){
                if (att.getActionType()==Const.ACTION_TYPE_INSERT){
                    att.setDataId(address.getId());
                    attachmentMapper.update(att);
                }
            }
        }



        addressMapper.update(address);
        AddressContact addressContact = address.getAddressContact();
        if (addressContact!=null){
            addressContactMapper.addressContactModify(addressContact);
        }else {
            AddressContact obj = new AddressContact();
            obj.setAddressId(address.getId());
            addressContactMapper.addressContactAdd(obj);
        }



        //模糊查询所有子集节点list   根据addressCode 模糊查询
        List<Address> findListByLikeCode = addressMapper.findAddListLikeCode(address.getAddressCode());
        if (findListByLikeCode != null){
            List<Address> addresses = new ArrayList<>();
            for (Address addressobj : findListByLikeCode) {
                Address obj = new Address();
                String addressDetaiLOld = addressobj.getAddressDetail();
                if (!address.getAddressLevel().equals(addressobj.getAddressLevel())){
                    obj.setId(addressobj.getId());
                    String addressDe= getAddressDetail(addressDetaiLOld, address.getAddressLevel()+3);
                    String addressDetailNew=addressDetail+addressDe;
                    obj.setAddressDetail(addressDetailNew);
                    addresses.add(obj);
                }
            }
            if (addresses.size()>0){
                addressMapper.updateAddressCodeBatch(addresses);
            }
        }
    }
    /**
     * 修改房屋
     * @param address
     */
    @Override
    @Transactional
    public void roomModify(Address address) {
        CheckUtil.notNull(address.getId(),"id不能为空");
        CheckUtil.notNull(address.getAddressName(),"地址名称不能为空");
        CheckUtil.notNull(address.getAddressCode(),"地址编码不能为空");
        CheckUtil.notNull(address.getFid(),"fid不能为空");
        String addressDetail="";
        Address byFid = addressMapper.findByPrimaryKey(address.getFid());
        addressDetail=byFid.getAddressDetail()+","+address.getAddressName();
        address.setAddressDetail(addressDetail);
        addressMapper.update(address);
        //模糊查询所有子集节点list   根据addressCode 模糊查询
        List<Address> findListByLikeCode = addressMapper.findAddListLikeCode(address.getAddressCode());
        if (findListByLikeCode != null){
            List<Address> addresses = new ArrayList<>();
            for (Address addressobj : findListByLikeCode) {
                Address obj = new Address();
                if (!address.getAddressLevel().equals(addressobj.getAddressLevel())){
                    obj.setId(addressobj.getId());
                    String addressDetailvalue = getAddressDetail(addressobj.getAddressDetail(), address.getAddressLevel()+3 );
                    String addressDetailNew=addressDetail+addressDetailvalue;
                    obj.setAddressDetail(addressDetailNew);
                    addresses.add(obj);
                }
            }
            if (addresses.size()>0){
                addressMapper.updateAddressCodeBatch(addresses);
            }
        }
    }

    @Override
    public Address findTopBySubset(String addressCode) {
        //处理addressCode，得出顶级小区code
        String sub= AddressUtil.sub(addressCode);
        Address topAdress = addressMapper.findByAddressCode(sub);
        return topAdress;
    }

    /**
     * 启动或者停止
     * @param id
     */
    @Override
    public void updateStartOrStop(Long id,Integer villageState) {
        Address address = new Address();
        address.setId(id);
        address.setVillageState(villageState);
        addressMapper.updateStartOrStop(address);
    }

    private  boolean equalList(List list1, List list2) {
        if (list1.size() != list2.size()){
            return false;
        }

        if (list2.containsAll(list1)){
            return true;
        }

        return false;
    }


}
