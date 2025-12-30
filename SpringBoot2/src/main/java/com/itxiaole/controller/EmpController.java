package com.itxiaole.controller;

import com.itxiaole.pojo.Emp;
import com.itxiaole.pojo.EmpQueryParam;
import com.itxiaole.pojo.PageResult;
import com.itxiaole.pojo.Result;
import com.itxiaole.service.EmpService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Resource
    private EmpService service;

    @GetMapping
    public Result page(EmpQueryParam  e) {
        PageResult<Emp> pageResult = service.page(e);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp e) {
        service.save(e);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工:{}",ids);
        service.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectAllById(@PathVariable Integer id) {
        Emp emps = service.selectAllById(id);
        return Result.success(emps);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp) {
        service.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectAll() {
        List<Emp> All = service.selectAll();
        return Result.success(All);
    }


}
