package com.lz.service.impl;

import com.lz.entity.vo.CommentVO;
import com.lz.result.RespResult;
import com.lz.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author :Lictory
 * @date : 2024/04/17
 */

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public RespResult<Void> addComment(String videoId, String comment) {
        return null;
    }

    @Override
    public RespResult<Void> deleteComment(String videoId) {
        return null;
    }

    @Override
    public RespResult<CommentVO> getComments(String videoId) {
        return null;
    }
}
