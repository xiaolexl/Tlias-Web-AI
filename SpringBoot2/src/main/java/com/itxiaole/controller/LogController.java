package com.itxiaole.controller;

import com.itxiaole.pojo.Emp;
import com.itxiaole.pojo.OperateLog;
import com.itxiaole.pojo.PageResult;
import com.itxiaole.pojo.Result;
import com.itxiaole.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService service;


    @GetMapping("/page")
    public Result log(Integer page,Integer pageSize){
        PageResult<OperateLog> pageResult = service.page(page, pageSize);
        return Result.success(pageResult);
    }
}
