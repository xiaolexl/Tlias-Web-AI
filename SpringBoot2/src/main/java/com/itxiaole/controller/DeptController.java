package com.itxiaole.controller;

import com.itxiaole.anno.Log;
import com.itxiaole.pojo.Dept;
import com.itxiaole.pojo.Result;
import com.itxiaole.service.DeptService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    @Resource
    private DeptService service;

//    @RequestMapping(value = "/depts",method = RequestMethod.POST)
    @GetMapping("/depts")
    public Result getdepts(){
        List<Dept> list = service.getDepts();
        return Result.success(list);
    }
    @Log
    @DeleteMapping("/depts")
    public Result deleteDepts(Integer id){
        service.deleteDepts(id);
        return Result.success();
    }
    @Log
    @PostMapping("/depts")
    public Result addDepts(@RequestBody Dept dept){
        service.addDepts(dept);
        return Result.success();
    }
    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable Integer id){
        Dept dept = service.getById(id);
        return Result.success(dept);
    }
    @Log
    @PutMapping("/depts")
    public Result updateDepts(@RequestBody Dept dept){
        service.updateDepts(dept);
        return Result.success();
    }

}
