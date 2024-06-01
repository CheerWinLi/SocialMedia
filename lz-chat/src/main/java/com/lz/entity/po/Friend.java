package com.lz.entity.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * (Friend)实体类
 *
 * @author makejava
 * @since 2024-05-29 09:08:24
 */
@Data
@TableName(value="friend")
@AllArgsConstructor
public class Friend implements Serializable {
    private static final long serialVersionUID = -92078781987130541L;

     /**
     关联user表
     */
     @TableId(type = IdType.AUTO)
     private Integer userId;

     /**
     关联user表
     */
    @TableField(value="friend_id")
    private Integer friendId;

     /**
     0代表A->B发送好友请求仍在申请,1代表建立好友关系,2代表A单删B
     */
    @TableField(value="status")
    private Integer status;



}

