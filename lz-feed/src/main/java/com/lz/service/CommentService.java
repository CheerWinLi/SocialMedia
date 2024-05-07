package com.lz.service;

import com.lz.entity.vo.CommentVO;
import com.lz.result.RespResult;
import org.springframework.stereotype.Service;

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
    RespResult<Void> addComment(String videoId, String comment);

    /**
     *
     * @param videoId
     * @return
     */
    RespResult<Void> deleteComment(String videoId);

    /**
     *
     * @param videoId
     * @return
     */
    RespResult<CommentVO> getComments(String videoId);
 }
