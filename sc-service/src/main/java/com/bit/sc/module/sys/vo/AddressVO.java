package com.bit.sc.module.sys.vo;

import com.bit.base.vo.BasePageVo;
import com.bit.sc.module.attachment.pojo.Attachment;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * AddressVo 类
 * @author   liqi
 */
@Data
public class AddressVO extends BasePageVo {
    //columns START

    /**
     * id
     */
    private Long id;
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 地址编码关联code
     */
    private String addressCode;
    /**
     * 数据的等级
     */
    private Integer addressLevel;
    /**
     * 父id
     */
    private Long fid;
    /**
     * 对应 sys_area_code 的code
     */
    private  String areaCode;
    /**
     * 小区编码 --只有小区有需要传递给公安用
     */
    private Integer valueCode;
    /**
     * 街道
     */
    private String street;
    /**
     * 门派号
     */
    private String plate;
    /**
     * 小区状态  0：停止 1：启动
     */
    private Integer villageState;
    /**
     * 备注
     */
    private String remark;
    /**
     * 街道门牌
     */
    private String streetPlate;
    /**
     * 地址详情
     */
    private String addressDetail;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 行政代码名称
     */
    private String arName;
    /**
     *  是否正式命名 0  是  1 否
     */
    private Integer isFormal;
    /**
     * 是否临时编制 0  是  1 否
     */
    private Integer isTemporary;
    /**
     * 设备预留字段
     */
    private Integer equipmentValue;
    /**
     * 楼栋大门类型
     */
    private Integer buildGateType;
    /**
     * 文件list
     */
    private List<Attachment> fileList;
    /**
     * 方便查询树结构
     */
    private List<AddressVO> childrenList;

    //columns END
}
