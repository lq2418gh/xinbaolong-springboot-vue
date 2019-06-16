package com.bit.sc.module.user.vo;

import lombok.Data;
import java.util.List;
@Data
public class UserAddressVO {
    private Long id;
    private Long userId;
    private List<Long> addressIds;

}
