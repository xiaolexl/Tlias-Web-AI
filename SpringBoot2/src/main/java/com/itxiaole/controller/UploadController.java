package com.itxiaole.controller;

import com.itxiaole.pojo.Result;
import com.itxiaole.untils.AliyunOSSOperator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
public class UploadController {
    //  本地存储
//    @PostMapping("/upload")
//    public Result upload(String name,Integer age, MultipartFile file) throws IOException {
//        log.info("接受参数:{},{},{}",name,age,file);
//        //获取原始链接
//        String originalFilename = file.getOriginalFilename();
//        //保存文件
//        file.transferTo(new File("C:/Users/20139/Desktop/JAVA/"+originalFilename));
//        return Result.success();
//    }

    @Resource
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {

        String url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());

        return Result.success(url);
    }
}
