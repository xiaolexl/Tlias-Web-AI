package com.itxiaole.controller;

import com.itxiaole.pojo.JobOption;
import com.itxiaole.pojo.Result;
import com.itxiaole.pojo.StudentOption;
import com.itxiaole.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("report")
@RestController
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工人数");
        JobOption job = service.getEmpJobData();
        return Result.success(job);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计性别人数");
        List<Map<String,Integer>> data = service.getEmpGenderData();
        return Result.success(data);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数");
        StudentOption data = service.getStudentCountData();
        return Result.success(data);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历信息");
        List<Map<String, Object>> data = service.getDegreeOption();
        return Result.success(data);
    }
}
