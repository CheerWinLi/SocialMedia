package com.lz.controller;

import com.lz.client.IpfsClient;
import com.lz.constant.ResultConstant;
import com.lz.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author :Lictory
 * @date : 2024/04/16
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IpfsClient ipfsClient;

    @PostMapping("/upload")
    CommonResult<String> uploadFile(MultipartFile file) {
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE, ipfsClient.upload(file));
    }

}
