package com.bit.sc.module.sys.service.impl;

import com.bit.base.vo.BaseVo;
import com.bit.sc.ScServerApplication;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.module.sys.service.DictService;
import com.bit.sc.module.sys.vo.AddressVO;
import com.bit.sc.module.sys.vo.DictVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ScServerApplication.class)
public class DictServiceImplTest {

    @Autowired
    private DictService dictService;
    @Test
    public void getModule() throws Exception {
        String module = "ga_document";
        List<Dict> dictList = dictService.getModule(module);
    }

//    @Test
//    public void queryDictByModuleCode() throws Exception {
//        String module = "ga_document";
//        String dictcode = "211";
//        Dict dict = dictService.queryDictByModuleCode(module,dictcode);
//    }
//
//    @Test
//    public void listPageByModule() throws Exception {
//        DictVO dictVO = new DictVO();
//        dictVO.setModule("ga_jobtype");
//        BaseVo baseVo = dictService.listPageByModule(dictVO);
//    }
//
//    @Test
//    public void listPageDictByModuleLikeValue() throws Exception {
//        DictVO dictVO = new DictVO();
//        dictVO.setModule("ga_jobtype");
//        dictVO.setValue("人");
//        BaseVo baseVo = dictService.listPageDictByModuleLikeValue(dictVO);
//    }
//
//    @Test
//    public void getDictByModuleLikeValue() throws Exception {
//        String module = "ga_document";
//        String value = "中";
//        List<Dict> dictList = dictService.getDictByModuleLikeValue(module,value);
//    }


}