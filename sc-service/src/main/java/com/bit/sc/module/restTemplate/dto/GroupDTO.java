package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-04 9:45
 * 分组实体类
 */
@Data
public class GroupDTO extends ResultDTO {
    /**
     * 分组id
     */
    private String groupId;
    /**
     * 分组名
     */
    private String groupName;
    /**
     * 每日启用时间，格式：HH:mm:ss
     */
    private String dayBeginTime;
    /**
     * 每日结束时间，格式：HH:mm:ss
     */
    private String dayEndTime;
    /**
     * 工作天数，0,1,2,3,4,5,6 分别表示周一到周日
     */
    private List<String> weekdays;
    /**
     * 分组id list
     */
    private List<String> groupIdList;

    /**
     * 组织id
     */
    private String orgId;
}
