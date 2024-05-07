package com.lz.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Likes)实体类
 *
 * @author makejava
 * @since 2024-04-17 10:26:01
 */
@Data
@Accessors(chain = true)
public class Likes implements Serializable {
    private static final long serialVersionUID = -77509897738216972L;
    /**
     * 主键
     */
    private String id;
    /**
     * 被谁点赞
     */
    private String likeBy;

}

