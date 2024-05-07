package com.lz.service.impl;

import com.lz.result.RespResult;
import com.lz.service.LikeService;
import org.springframework.stereotype.Service;

/**
 * @author :Lictory
 * @date : 2024/04/17
 */

@Service
public class LikeServiceImpl implements LikeService {

    @Override
    public RespResult<Void> addLike(String videoId, String userId) {
        return null;
    }

    @Override
    public RespResult<Void> deleteLike(String videoId, String userId) {
        return null;
    }
}
