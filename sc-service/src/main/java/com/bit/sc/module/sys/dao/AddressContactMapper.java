package com.bit.sc.module.sys.dao;

import com.bit.sc.module.sys.pojo.AddressContact;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenduo
 * @create 2018-12-28 10:36
 */
public interface AddressContactMapper {

    void addressContactAdd(AddressContact addressContact);

    void addressContactModify(AddressContact addressContact);

    void addressContactDelete(@Param(value = "id") Long id);

    AddressContact findById(@Param(value = "id") Long id);

    AddressContact findByAddressId(@Param(value = "addressId") Long addressId);
}
