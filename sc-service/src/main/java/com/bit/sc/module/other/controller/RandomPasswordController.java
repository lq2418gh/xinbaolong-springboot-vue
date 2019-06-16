package com.bit.sc.module.other.controller;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.group.service.GroupRelService;
import com.bit.sc.module.other.vo.RandomVo;
import com.bit.sc.utils.MD5;
import com.bit.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/random")
public class RandomPasswordController extends BaseService {
    @Autowired
    CacheUtil cacheUtil;
    @Autowired
    GroupRelService groupRelService;

    @GetMapping("/createRandSixPassword/{equId}")
    public BaseVo createRandSixPassword(@PathVariable(value = "equId") Long equId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //人的id
        Long userid = getCurrentUserInfo().getId();
        String qrCode= String.valueOf(userid)+":"+String.valueOf(equId);
        if (cacheUtil.get(qrCode)!=null){
            cacheUtil.del(qrCode);
        }
        Map<String, Object> map = new HashMap<>();
        int i = this.checkGroup(equId);
        if (i==1){
            map.put("erro","对不起，您没有开启此设备的权限！！");
        }
        if (i==2){
            map.put("erro","对不起，请从新登陆！！");
        }
        if (i==0){
            //随机数字
            String num = String.valueOf((Math.random() * 9 + 1) * 100000);
            //加密随机数
            String value = MD5.EncoderByMd5(num);
            //返回值
            map.put("userId",userid);
            map.put("equId",equId);
            map.put("randomSixPassword",value);
            map.put("time",3600);
            //存储redis
            cacheUtil.set(String.valueOf(userid)+":"+String.valueOf(equId),value, 3600);
        }
        BaseVo baseVo = new BaseVo();
        baseVo.setData(map);
        return baseVo;
    }

    /**
     * 校验分组
     * @param equId
     */
    private int checkGroup(Long equId) {
        GroupRel groupRel = new GroupRel();
        //人的id
        Long userid = getCurrentUserInfo().getId();
        if (userid!=null){
            //人的type
            int userType=Const.GROUP_REL_RESIDENT_TYPE;
            //根据用户id 和type  查询分组表  拿到 人下的所有分组
            groupRel.setType(userType);
            groupRel.setRelId(userid);
            List<GroupRel> byParam = groupRelService.findByParam(groupRel);
            String userGreoup="";
            //此用户下的所有分组 组ids
            if (byParam!=null&&byParam.size()>0){
                userGreoup=this.forList(byParam);
            }else {
                return 1;
            }


            //---------上面是人的分组-----------------下面是设备的分组--------------------------------------------

            //设备的type
            Integer equiType=Const.GROUP_REL_EQUIPMENT_TYPE;
            groupRel.setType(equiType);
            groupRel.setRelId(equId);
            //此设备下的所有的分组 组ids
            List<GroupRel> byParam2 = groupRelService.findByParam(groupRel);
            String equGroup="";
            if (byParam!=null&&byParam2.size()>0){
                equGroup=this.forList(byParam2);
            }else {
                return 1;
            }

            //---------------------------------------两个分组的对比校验--------------------------------------------------------
            //比较人下的分组 和设备下的分组
            //1、如果有相同的分组证明 此人可以开启此设备
            //2、如果没有相同的分组 证明   此人在这个分组下没有此设备  所以没有权限开启
            String[] split1 = equGroup.split(",");
            for (String s : split1) {
                if (userGreoup.indexOf(s)!=-1){
                    return 0;
                }
            }
        }else {
            return 2;
        }
        return 1;
    }

    private String forList(List<GroupRel> list) {
        String str="";
        for (GroupRel rel : list) {
            Long groupId = rel.getGroupId();
            str+=groupId+",";
        }
        str = str.substring(0,str.length()-1);
        return  str;
    }

    @PostMapping("/checkQrCode")
    public  BaseVo checkQrCode(@RequestBody RandomVo randomVo){
        BaseVo baseVo = new BaseVo();
        Long userid = getCurrentUserInfo().getId();
        String redisQrCode= (String)cacheUtil.get(userid + ":" + randomVo.getDeviceId());
        if (redisQrCode==null){
            baseVo.setCode(1);
            baseVo.setData("密码失效");
        }
        if (redisQrCode!=randomVo.getQrCode()){
            baseVo.setCode(1);
            baseVo.setData("密码不对.请从新输入");
        }
        return baseVo;
    }

}
