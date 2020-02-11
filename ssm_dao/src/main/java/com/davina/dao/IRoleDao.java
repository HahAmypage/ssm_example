package com.davina.dao;

import com.davina.domain.Permission;
import com.davina.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    @Select("SELECT * FROM sys_role")
    List<Role> findAllRoles();

    /**
     * 添加角色
     * @param role
     */
    @Insert("INSERT INTO sys_role VALUES(SEQ_ROLE.nextval,#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("SELECT r.* FROM sys_user_role ur INNER JOIN sys_role r ON ur.roleid = r.id WHERE ur.userid = #{userId}")
    @Results({
            /**
             * 封装角色信息
             */
            @Result(id = true,property = "id",column = "id"),

            /**
             * 封装权限信息
             */
            @Result(property = "permissions",column = "id",javaType = List.class,
            many = @Many(select = "com.davina.dao.IPermissionDao.findPermissionByRoleId",fetchType = FetchType.LAZY))
    })
    List<Role> findRolesByUserId(Integer userId);
}
