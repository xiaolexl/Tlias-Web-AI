package com.itxiaole.controller;

import com.itxiaole.pojo.Clazz;
import com.itxiaole.pojo.ClazzsQueryParam;
import com.itxiaole.pojo.PageResult;
import com.itxiaole.pojo.Result;
import com.itxiaole.service.ClazzsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzsController {

    @Autowired
    ClazzsService service;

    @GetMapping
    public Result page(ClazzsQueryParam param){
        PageResult<Clazz> pageResult = service.page(param);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        service.add(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer id){
        Clazz clazz = service.getValueById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        service.update(clazz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        service.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Clazz> list = service.list();
        return Result.success(list);
    }


}
