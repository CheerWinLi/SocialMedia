package com.lz.entity.po;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * (GroupUser)实体类
 *
 * @author makejava
 * @since 2024-05-07 14:56:04
 */
@Data
@TableName(value="group_user")
public class GroupUser implements Serializable {
    private static final long serialVersionUID = -57538355912554670L;

     /**
     群id
     */
    @TableField(value="group_id")
    private Integer groupId;

     /**
     关联user
     */
    @TableField(value="user_id")
    private Integer userId;



}

