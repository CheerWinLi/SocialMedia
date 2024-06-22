package com.lz.controller;

import com.lz.client.IpfsClient;
import com.lz.constant.ResultConstant;
import com.lz.result.CommonResult;
import com.lz.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author :Lictory
 * @date : 2024/04/16
 */

@RestController
@RequestMapping("/file")
public class FileController {


    @Autowired
    private IpfsClient ipfsClient;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    CommonResult<String> uploadFile(MultipartFile file) {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE, ipfsClient.upload(file));
    }

    @PostMapping("/fragment/upload")
    CommonResult<Boolean> uploadFragmentFile(MultipartFile file, String md5, Integer currentIndex, Integer total, String originalFileName, String fileType) {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE, fileService.uploadFragmentFile(file, md5, currentIndex, total, originalFileName, fileType));
    }

    @PostMapping("/fragment/list")
    CommonResult<Set<Object>> getFragmentIndexList(String md5) {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE, fileService.getFragmentIndexList(md5));
    }

}
