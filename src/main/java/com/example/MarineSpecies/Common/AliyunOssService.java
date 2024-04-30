package com.example.MarineSpecies.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * 阿里云OSS库调用封装类
 *
 * @author ************
 * @since 2024-04-11
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@PropertySource(value = {"classpath:oss.properties"})
public class AliyunOssService {

    private String endPoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
    public String uploadFile(MultipartFile file, String dest) {
        InputStream inputStream;
        try {
            OSS ossClient = new OSSClientBuilder().build(endPoint, keyId, keySecret);
            //获取上传文件输入流
            inputStream = file.getInputStream();

            //获取文件名称
            String fileName = Objects.requireNonNull(file.getOriginalFilename()).replaceAll(",", "");
            //使文件名称唯一，可实现多次上传相同文件
            String s = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = s + fileName;

            String filePath = dest + "/" + fileName;
            log.info("filename:{}",filePath);
            //第一个参数 bucket名称
            //第二个参数 上传oss文件名称和路径
            //第三个参数，上传输入流
            ossClient.putObject(bucketName, filePath, inputStream);
            //关闭OssClient
            ossClient.shutdown();

            //上传阿里云之后的文件路径返回
            return "https://" + bucketName + "." + endPoint + "/" + filePath;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String uploadFiles(MultipartFile[] files, String dest){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<files.length;i++){
            try{
                result.append(uploadFile(files[i], dest));
                if(i!=files.length-1){
                    result.append(",");
                }
            }catch (Exception e){
                log.error(e.toString());
            }
        }
        return result.toString();
    }

}
