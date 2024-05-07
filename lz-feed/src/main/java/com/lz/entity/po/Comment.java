package com.lz.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2024-04-17 10:25:20
 */


@Data
@Accessors(chain = true)
public class Comment implements Serializable {
    private static final long serialVersionUID = 679649940196934721L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 评论人
     */
    private String commentBy;
    /**
     * 评论的视频
     */
    private Integer commentVideo;
    /**
     * 评论内容
     */
    private String comment;



}

