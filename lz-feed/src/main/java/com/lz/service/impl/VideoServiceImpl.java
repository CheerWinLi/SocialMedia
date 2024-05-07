package com.lz.service.impl;

import cn.hutool.core.lang.Assert;
import com.lz.client.FileClient;
import com.lz.constant.ResultConstant;
import com.lz.constant.WebSecurityConstant;
import com.lz.entity.po.Video;
import com.lz.entity.vo.VideoVO;
import com.lz.mapper.VideoMapper;
import com.lz.result.RespResult;
import com.lz.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author :Lictory
 * @date : 2024/04/17
 */

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private FileClient fileClient;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public RespResult<Void> postVideo(MultipartFile file, String username) {
        String cid = fileClient.upload(file).getData();

        Assert.isTrue(videoMapper.insert(new Video()
                .setCid(cid)
                .setCreateTime(new Date(System.currentTimeMillis()))
                .setUploadBy(username)
                .setDeleted(0)) > 0, "上传视频失败");
        return new RespResult<Void>(ResultConstant.SUCCESS_CODE, "上传视频成功");
    }

    @Override
    public RespResult<VideoVO> getVideosFromUser(String username) {
        return null;
    }

    @Override
    public RespResult<VideoVO> getVideosForRandom() {
        return null;
    }
}
