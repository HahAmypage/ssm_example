package com.davina.service;

import com.davina.domain.Permission;
import com.github.pagehelper.PageInfo;

public interface IPermissionService {

    /**
     * 分页查询所有权限
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Permission> findByPage(int pageNum,int pageSize);

    /**
     * 添加权限
     * @param permission
     */
    void save(Permission permission);
}
