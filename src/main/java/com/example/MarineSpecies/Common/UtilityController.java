package com.example.MarineSpecies.Common;

import com.example.MarineSpecies.LoginManager.Entity.DTO.SignedIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

/**
 * 一些常用的共享接口
 *
 * @author ************
 * @since 2024-04-11
 */
@RestController
@Slf4j
public class UtilityController {
    @Autowired
    AliyunOssService aliyunOssService;

    /**
     * 单图片上传
     *
     * @param file 文件类型
     * @param signedIn 使用者的session参数
     * @return 公网访问链接
     */
    @PostMapping("uploadImage")
    public MyResponse<String> uploadImage(@RequestParam("image") MultipartFile file, @SessionAttribute("signedIn") SignedIn signedIn){
        return MyResponse.ok(aliyunOssService.uploadFile(file,"marine"));
    }


    /**
     * 多图片上传
     *
     * @param files 文件列表
     * @param signedIn 使用者的session参数
     * @return 公网访问链接，各图片之间使用逗号隔开
     */
    @PostMapping("/uploadImages")
    public MyResponse<String> uploadImages(@RequestParam("images")MultipartFile[] files, @SessionAttribute("signedIn") SignedIn signedIn){
        return MyResponse.ok(aliyunOssService.uploadFiles(files,"marine"));
    }
}
