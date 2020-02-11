package com.davina.service.impl;

import com.davina.dao.IRoleDao;
import com.davina.domain.Role;
import com.davina.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    /**
     * 分页查询所有角色
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Role> findAllRoles(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(roleDao.findAllRoles());
    }

    /**
     * 新增角色
     *
     * @param role
     */
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> findAll(){
        return roleDao.findAllRoles();
    }
}
