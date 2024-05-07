package com.lz.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (Video)实体类
 *
 * @author makejava
 * @since 2024-04-17 09:46:02
 */

@Data
@Accessors(chain = true)
public class Video implements Serializable {
    private static final long serialVersionUID = 820954790129140793L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * cid
     */
    private String cid;

    @TableField("upload_by")
    private String uploadBy;

    @TableField("create_time")
    private Date createTime;

    /**
     * 逻辑删除 0存在  1删除
     */
    private Integer deleted;

}

