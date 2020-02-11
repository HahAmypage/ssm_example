package com.davina.service.impl;

import com.davina.dao.IUserDao;
import com.davina.domain.Role;
import com.davina.domain.SysUser;
import com.davina.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 根据用户名查询: 参数就是用户输入的用户名。
        List<SysUser> userList = userDao.findUserByUsername(username);
        //2. 判断
        if (userList == null || userList.size() == 0){
            // 用户名不存在，认证失败。
            return null;
        }

        //3. 获取用户对象
        SysUser sysUser = userList.get(0);

        //从数据库获取用户权限
        List<Role> roles = sysUser.getRoles();

        // 用户的权限，先写死
        List<GrantedAuthority> authorityList = new ArrayList<>();

        if (roles != null && roles.size() > 0){
            for (Role role : roles){
                authorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        //4. 返回UserDetails，告诉springsecurity数据库中：正确的账号密码、以及用户的权限。
        // 参数1: 数据库中正确的用户名
        // 参数2：数据库中用户名对应的正确的密码(注意这里要设置密码不加密)
        // 参数3：用户具有的权限
        UserDetails userDetails = new User(username,sysUser.getPassword(),authorityList);

        return userDetails;
    }

    /**
     * 分页查询所有用户
     * @return
     */
    @Override
    public PageInfo<SysUser> findAllUsers(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> allUsers = userDao.findAllUsers();
        PageInfo<SysUser> userPageInfo = new PageInfo<>(allUsers);
        return userPageInfo;
    }

    /**
     * 新增用户
     * @param sysUser
     */
    @Override
    public void save(SysUser sysUser) {
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        userDao.save(sysUser);
    }

    /**
     * 根据id查用户
     * @param id
     * @return
     */
    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    /**
     * 更改权限
     * @param userid
     * @param roleIds
     */
    @Override
    public void addRoleToUser(Integer userid, Integer[] roleIds) {

        //先删除原来的角色
        userDao.deleteRolesById(userid);

        //把新的角色添加进去
        if (roleIds != null && roleIds.length > 0){
            for (Integer roleid : roleIds){
                userDao.addRoleToUser(userid,roleid);
            }
        }
    }
}
