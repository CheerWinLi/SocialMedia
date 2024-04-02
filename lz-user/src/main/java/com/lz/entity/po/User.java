package com.lz.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-03-03 16:54:12
 */
@Accessors(chain = true)
@Data
@TableName
public class User implements Serializable {
    private static final long serialVersionUID = 982932181330687492L;
    /**
     * 主键 唯一id
     */
    private Long id;
    /**
     * 账户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否删除 -1:已删除  0:正常
     */
    @TableField(value = "del_flag")
    private Integer delFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 邮箱
     */
    private String email;

//    /**
//     * 性别 0 : 女      1: 男
//     */
//    private Integer gender;
}

