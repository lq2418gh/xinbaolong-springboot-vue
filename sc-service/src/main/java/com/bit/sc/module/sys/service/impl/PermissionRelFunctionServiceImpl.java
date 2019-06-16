package com.bit.sc.module.sys.service.impl;

import com.bit.sc.module.sys.dao.PermissionRelFunctionMapper;
import com.bit.sc.module.sys.pojo.PermissionRelFunction;
import com.bit.sc.module.sys.service.PermissionRelFunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionRelFunctionServiceImpl implements PermissionRelFunctionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionRelFunctionServiceImpl.class);

    @Autowired
    private PermissionRelFunctionMapper permissionRelFunctionMapper;

    /**
     * 查询所有PermissionRelFunction
     * @param permissionType
     * @return
     */
    @Override
    public List<PermissionRelFunction> findAll(Integer permissionType) {
        return permissionRelFunctionMapper.findAll(permissionType);
    }
}
