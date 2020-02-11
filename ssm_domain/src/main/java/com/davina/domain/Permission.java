package com.davina.domain;

public class Permission {

    private Long id;
    private String permissionName;
    private String url;
    private Long pid;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Permission(Long id, String permissionName, String url, Long pid) {
        this.id = id;
        this.permissionName = permissionName;
        this.url = url;
        this.pid = pid;
    }

    public Permission() {
    }
}
