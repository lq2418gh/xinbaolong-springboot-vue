package com.bit.sc.module.house.vo;

import com.bit.sc.module.house.pojo.RealPerson;
import com.bit.sc.module.house.pojo.RegisteredResident;
import lombok.Data;

import java.util.List;

@Data
public class HouseSubmitVO {

    private List<RealPersonVO> realPersonVOList;

    private List<RegisteredResidentVO> registeredResidentVOList;

    private HouseVO houseVO;
}
