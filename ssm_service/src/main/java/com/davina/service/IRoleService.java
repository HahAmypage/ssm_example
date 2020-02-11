package com.davina.service;

import com.davina.domain.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {

    /**
     * 查询所有角色
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Role> findAllRoles(int pageNum,int pageSize);

    /**
     * 新增角色
     * @param role
     */
    void save(Role role);

    List<Role> findAll();
}
