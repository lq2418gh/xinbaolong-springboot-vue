package com.bit.sc.module.resident.vo;

import com.bit.sc.module.resident.pojo.ResidentDoorCard;
import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import lombok.Data;

import java.util.List;

@Data
public class ResidentReflectVO {

    private ResidentDictVO residentDictVO;

    private List<ResidentRelAddress> residentRelAddressList;

    private List<Long> relIds;

    private List<ResidentDoorCard> residentDoorCardList;
}
