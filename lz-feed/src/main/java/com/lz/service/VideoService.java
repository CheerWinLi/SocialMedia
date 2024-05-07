package com.lz.service;

import com.lz.entity.vo.VideoVO;
import com.lz.result.RespResult;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author :Lictory
 * @date : 2024/04/17
 */

public interface VideoService {


    /**
     *
     * @param file
     * @param username
     * @return
     */
    RespResult<Void> postVideo(@RequestPart("file") MultipartFile file, String username);

    /**
     *
     * @param username
     * @return
     */
    RespResult<VideoVO> getVideosFromUser(@RequestParam("username") String username);


    /**
     *
     * @return
     */
    RespResult<VideoVO> getVideosForRandom();
}
