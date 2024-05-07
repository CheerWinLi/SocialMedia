package com.lz.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * (Group)实体类
 *
 * @author makejava
 * @since 2024-05-07 14:56:06
 */
@Data
@TableName(value="group")
public class Group implements Serializable {
    private static final long serialVersionUID = 660706698525530797L;

     /**
     主键,群唯一标识
     */
    @TableField(value="id")
    private Integer id;

     /**
     群名
     */
    @TableField(value="group_name")
    private String groupName;

}

