package com.lz.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.lz.service.FileService;
import com.lz.util.MinioUtil;
import com.lz.util.RedisUtil;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author :Lictory
 * @date : 2024/06/21
 */

@Service
public class FileServiceImpl implements FileService {

    private static final String TEMP_BUCKET = "TEMP_BUCKET";

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Boolean uploadFragmentFile(MultipartFile file, String md5, Integer currenIndex, Integer total, String originalFileName, String fileType) {
        //逻辑 不存在则创建
        minioUtil.doesBucketExist(TEMP_BUCKET);

        //使用md5+当前分片号作为对象名
        String objectName = md5 + "/" + currenIndex;
        //存入缓存bucket
        minioUtil.uploadFragmentFile(TEMP_BUCKET, file, objectName, fileType);
        redisUtil.setRemove(md5, currenIndex);
        if (redisUtil.sGetSetSize(md5) == 0) {
            //上传完成
            //获取各个分片名
            List<String> objectNames = Stream.of(total).map(i -> md5 + "/" + i).collect(Collectors.toList());
            //合并
            Assert.isTrue(minioUtil.composeFile(TEMP_BUCKET, originalFileName, objectNames), md5 + "分片合并失败");
            //删除缓存bucket
            minioUtil.deleteFiles(TEMP_BUCKET, objectNames);
        }
        //返回传输的index+1
        return true;
    }

    @Override
    public Boolean initFragmentUpload() {
        return null;
    }

    @Override
    public Set<Object> getFragmentIndexList(String md5) {
        //TODO 查询数据库
        //存在md5直接秒传

        //存在md5,开始断点续传
        if (redisUtil.hasKey(md5)) {
            return  redisUtil.sGet(md5);
        } else {
            //不存在该md5,应从头传输
            return null;
        }
    }
}
