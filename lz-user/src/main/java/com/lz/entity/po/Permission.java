package com.lz.entity.po;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2024-03-03 16:53:59
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -96835351945224396L;
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 权限名字
     */
    private String permissionName;
    /**
     * 权限代号
     */
    private String permissionCode;


    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

}

