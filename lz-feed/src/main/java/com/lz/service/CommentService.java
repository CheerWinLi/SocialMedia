package com.lz.service;

import com.lz.entity.vo.CommentVO;
import com.lz.result.CommonResult;

/**
 * @author :Lictory
 * @date : 2024/04/17
 */

public interface CommentService {
    /**
     * @param videoId
     * @param comment
     * @return
     */
    CommonResult<Void> addComment(String videoId, String comment);

    /**
     *
     * @param videoId
     * @return
     */
    CommonResult<Void> deleteComment(String videoId);

    /**
     *
     * @param videoId
     * @return
     */
    CommonResult<CommentVO> getComments(String videoId);
 }
