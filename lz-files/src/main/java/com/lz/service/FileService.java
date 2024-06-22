package com.lz.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author :Lictory
 * @date : 2024/06/21
 */
public interface FileService {

    /**
     * upload file
     *
     * @param file        file
     * @param md5         md5
     * @param currenIndex 当前索引
     * @param total       总数
     * @return 操作状态
     */
    Boolean uploadFragmentFile(MultipartFile file, String md5, Integer currenIndex, Integer total, String originalFileName, String fileType);

    Boolean initFragmentUpload();

    Set<Object> getFragmentIndexList(String md5);

}
