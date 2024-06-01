package com.lz.entity.po;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (Group)实体类
 *
 * @author makejava
 * @since 2024-05-07 19:43:25
 */
@Data
@TableName(value = "group")
public class Group implements Serializable {
    private static final long serialVersionUID = -92432442708408472L;

    /**
     * 主键,群唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 群名
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 群头像地址
     */
    @TableField(value = "image_path")
    private String imagePath;

    /**
     * 群成员数量
     */
    @TableField(value = "member_count")
    private Integer memberCount;

    /**
     * 群主
     */
    @TableField(value = "group_owner")
    private String groupOwner;

    /**
     * 群管理员
     */
    @TableField(value = "group_manager")
    private String groupManager;

    /**
     * 聊天记录,关联record表
     */
    @TableField(value = "chat_record")
    private String chatRecord;


}

