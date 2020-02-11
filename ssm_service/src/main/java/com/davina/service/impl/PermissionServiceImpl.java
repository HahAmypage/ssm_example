package com.davina.service.impl;

import com.davina.dao.IPermissionDao;
import com.davina.domain.Permission;
import com.davina.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 分页查询所有权限
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Permission> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(permissionDao.findAllPermission());
    }

    /**
     * 添加权限
     *
     * @param permission
     */
    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
