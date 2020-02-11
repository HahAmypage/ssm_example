package com.davina.dao;

import com.davina.domain.Role;
import com.davina.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

public interface IUserDao {

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.davina.dao.IRoleDao.findRolesByUserId",fetchType = FetchType.LAZY))
    })
    List<SysUser> findUserByUsername(String username);

    @Select("SELECT * FROM sys_user")
    List<SysUser> findAllUsers();

    @Insert("INSERT INTO sys_user VALUES(SEQ_USER.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser sysUser);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    @Results({
            /**
             * 封装用户信息
             */
            @Result(id = true,property = "id",column = "id"),

            /**
             * 封装角色信息
             */
            @Result(property = "roles",column = "id",javaType = List.class,
            many = @Many(select = "com.davina.dao.IRoleDao.findRolesByUserId",fetchType = FetchType.LAZY))
    })
    SysUser findById(Integer id);

    /**
     * 根据用户id删角色
     * @param userid
     */
    @Delete("DELETE FROM sys_user_role ur WHERE ur.userid = #{userid}")
    void deleteRolesById(Integer userid);

    /**
     * 给用户添加角色
     * @param userid
     * @param roleid
     */
    @Insert("INSERT INTO sys_user_role VALUES(#{arg0},#{arg1})")
    void addRoleToUser(Integer userid, Integer roleid);
}
