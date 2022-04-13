package com.bjsxt.backend.item.service.impl;

import com.bjsxt.backend.item.service.FileUploadService;
import com.bjsxt.utils.FastDFSClient;
import com.bjsxt.utils.FtpUtil;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上传图片Service
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${fastdfs.nginx}")
    private String nginxHost;
    @Override
    public Result fileUpload(MultipartFile file) {
            try {
                /*
                 * file.getInputStream获取图片流
                 * file.getOriginalFilename()获取图片名称
                 * FastDFS图片会被FastDFS重新命名，第二个参数的意义是知道文件的扩展名
                 * 返回值是FastDFS中存储的名字
                 * */
                String[] result = FastDFSClient.uploadFile(file.getInputStream(), file.getOriginalFilename());
                String imageURL = nginxHost + result[0] + "/" + result[1];
                System.out.println(imageURL);
                return Result.ok(imageURL);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }
}
