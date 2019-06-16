package com.bit.sc.module.house.controller;

import com.bit.sc.ScServerApplication;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ScServerApplication.class)
@WebAppConfiguration
public class HouseControllerTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void test(){
        List<Address> root = addressService.findListByFid(0L);
        for (Address address : root){
            if (addressService.findListByFid(address.getId()).size()>0){

            }
        }
    }

}