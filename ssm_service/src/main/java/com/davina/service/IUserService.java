package com.davina.service;

import com.davina.domain.SysUser;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    /**
     * 分页查询所有用户
     * @return
     */
    PageInfo<SysUser> findAllUsers(int pageNum,int pageSize);

    /**
     * 新增用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    SysUser findById(Integer id);

    /**
     * 更改权限
     * @param userid
     * @param roleIds
     */
    void addRoleToUser(Integer userid, Integer[] roleIds);
}
