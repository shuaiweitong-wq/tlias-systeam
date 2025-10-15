package com.tsw.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
    String endpoint = "oss-cn-beijing.aliyuncs.com";
    @Value("${aliyun.oss.access-key-id}")
    String accessKeyId;
    @Value("${aliyun.oss.access-key-secret}")
    String accessKeySecret;
    String bucketName = "springboot-tilas-we";

    public String upload(MultipartFile file) throws Exception {
        // 获取上传文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        // 文件访问路径
        String url = "https://" + bucketName + "." + endpoint + "/" + fileName;

        // 关闭OSSClient
        ossClient.shutdown();

        return url;
    }
}
