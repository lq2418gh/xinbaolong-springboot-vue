package com.bit.sc.module.house.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * RegisteredResident
 * @author chenduo
 */
@Data
public class RegisteredResidentVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 姓名
     */	
	private String realName;
    /**
     * 身份证号码
     */	
	private String cardId;
    /**
     * 性别
     */	
	private Long sex;
    /**
     * 户籍地址
     */	
	private String hjdz;
    /**
     * 与户主关系
     */	
	private String relationOwner;
    /**
     * 联系电话
     */	
	private String mobile;


	//columns END

    /**
     * house表外键
     */
    private Long houseId;

    /**
     * 是否户主 0-是 1-不是
     */
    private Integer isHouseholder;

}


