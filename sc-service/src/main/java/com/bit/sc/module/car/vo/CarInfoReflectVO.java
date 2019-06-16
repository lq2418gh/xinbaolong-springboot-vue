package com.bit.sc.module.car.vo;

import com.bit.sc.module.car.pojo.CarInfo;
import lombok.Data;

@Data
public class CarInfoReflectVO {
    private CarInfo carInfo;
    private String attachmentPath;
}
