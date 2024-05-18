package com.lz.service;

import com.lz.result.CommonResult;

/**
 * @author :Lictory
 * @date : 2024/04/17
 */


public interface LikeService {

    /**
     *
     * @param videoId
     * @param userId
     * @return
     */
    CommonResult<Void> addLike(String videoId, String userId);

    /**
     *
     * @param videoId
     * @param userId
     * @return
     */
    CommonResult<Void> deleteLike(String videoId, String userId);

}
