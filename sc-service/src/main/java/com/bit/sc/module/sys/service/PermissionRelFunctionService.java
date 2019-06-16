package com.bit.sc.module.sys.service;

import com.bit.sc.module.sys.pojo.PermissionRelFunction;
import org.springframework.stereotype.Service;

import java.util.List;

    /**
     * PermissionRelFunction
     * @author zhangjie
     */
    @Service
    public interface PermissionRelFunctionService {
        /**
         * 查询所有PermissionRelFunction
         * @param permissionType
         * @return
         */
        public List<PermissionRelFunction> findAll(Integer permissionType);
}
