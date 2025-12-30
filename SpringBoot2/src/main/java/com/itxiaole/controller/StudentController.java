package com.itxiaole.controller;

import com.itxiaole.pojo.PageResult;
import com.itxiaole.pojo.Result;
import com.itxiaole.pojo.Student;
import com.itxiaole.pojo.StudentQueryParm;
import com.itxiaole.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;


    @GetMapping
    public Result page(StudentQueryParm sq){
        PageResult<Student> pageResult = service.page(sq);
        return  Result.success(pageResult);
    }

    @PostMapping
    public Result add(@RequestBody Student student){
        service.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer id){
        Student student = service.getById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        service.save(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable("ids") Integer ids){
        service.delete(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable("id") Integer id,@PathVariable("score") Integer score){
        service.vio(id,score);
        return Result.success();
    }
}
