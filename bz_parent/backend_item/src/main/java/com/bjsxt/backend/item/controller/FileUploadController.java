package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.FileUploadService;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 图片上传
     */
    @RequestMapping("/upload")
    public Result fileUpload(MultipartFile file){
        try{
            return this.fileUploadService.fileUpload(file);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");

    }
}
