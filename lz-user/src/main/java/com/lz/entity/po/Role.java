package com.lz.entity.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2024-03-03 16:52:44
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 819303207768267241L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色名字
     */
    private String roleName;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

