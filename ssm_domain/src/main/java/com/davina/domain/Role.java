package com.davina.domain;

import java.util.List;

public class Role {

    private Long id;
    private String roleName;
    private String roleDesc;
    // 角色关联的权限
    private List<Permission> permissions;


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", permissions=" + permissions +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role(Long id, String roleName, String roleDesc, List<Permission> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.permissions = permissions;
    }

    public Role() {
    }


}
