package com.tsw.controller;

import com.tsw.pojo.Result;
import com.tsw.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile image){
        try {
            log.info("文件上传，参数：{}", image);
            String url = aliOSSUtils.upload(image);
            log.info("文件上传成功，返回结果：{}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败：{}", e.getMessage());
            return Result.error("文件上传失败");
        }
    }
}
