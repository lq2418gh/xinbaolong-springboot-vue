package com.bit.sc.module.group.service;

import com.bit.sc.module.group.pojo.Group;
import org.springframework.stereotype.Service;

/**
 * 调第三方接口操作Group的Service
 * @author zhangjie
 * @date 2018-12-12
 */
@Service
public interface PlatformService {

    void addGroup(Group group);
    void updateGroup(Group group);
    void deleteGroup(Group group);
    void startGroup(Group group);
}
