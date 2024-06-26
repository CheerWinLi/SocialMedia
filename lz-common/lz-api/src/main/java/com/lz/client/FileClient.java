package com.lz.client;

import com.lz.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author :Lictory
 * @date : 2024/04/16
 */

@FeignClient(value = "lz-file")
public interface FileClient {
    /**
     * 远程调用ipfs上传文件/图片/视频并返回cid码
     * @param file 文件
     * @return 返回空值
     */
    @PostMapping(value = "/file/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResult<String> upload(@RequestPart("file") MultipartFile file);
}
