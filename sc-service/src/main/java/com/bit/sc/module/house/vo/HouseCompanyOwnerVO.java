package com.bit.sc.module.house.vo;

import com.bit.sc.module.house.pojo.*;
import lombok.Data;

import java.util.List;

@Data
public class HouseCompanyOwnerVO {

    private HouseNewVO houseNewVO;

    private RealCompany realCompany;

    private List<OwnerCustodianRent> ownerCustodianRentList;

    private List<RegisteredResident> registeredResidentList;

    private List<RealPerson> realPersonList;
}
