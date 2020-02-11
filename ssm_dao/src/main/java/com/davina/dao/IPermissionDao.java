package com.davina.dao;

import com.davina.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IPermissionDao {

    /**
     * 查询所有权限
     * @return
     */
    @Select("SELECT * FROM sys_permission")
    List<Permission> findAllPermission();

    /**
     * 添加权限
     * @param permission
     */
    @Insert("INSERT INTO sys_permission VALUES(SEQ_PERMISSION.nextval,#{permissionName},#{url},#{pid})")
    void save(Permission permission);

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    @Select("SELECT p.* FROM sys_role_permission rp INNER JOIN sys_permission p ON rp.permissionid = p.id WHERE rp.roleid = #{roleId}")
    List<Permission> findPermissionByRoleId(Integer roleId);
}
