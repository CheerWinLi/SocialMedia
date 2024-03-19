package com.lz.entity.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (UsrRole)实体类
 *
 * @author makejava
 * @since 2024-03-03 16:54:12
 */
public class UsrRole implements Serializable {
    private static final long serialVersionUID = -53823177967067223L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}

