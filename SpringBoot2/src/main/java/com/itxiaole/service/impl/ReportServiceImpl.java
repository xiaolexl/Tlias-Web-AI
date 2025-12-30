package com.itxiaole.service.impl;

import com.itxiaole.mapper.EmpMapper;
import com.itxiaole.mapper.StudentMapper;
import com.itxiaole.pojo.*;
import com.itxiaole.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper mapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {

//        List<Map<String, Object>> maps = mapper.countEmpJobData();
//        List<Object> jobList = new ArrayList<Object>();
//        List<Object> dataList = new ArrayList<Object>();
//        for (Map<String, Object> map : maps) {
//            jobList.add(map.get("pos").toString());
//            dataList.add(map.get("num").toString());
//        }
        List<Map<String, Object>> list = mapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Integer>> getEmpGenderData() {
        return mapper.countEmpGenderData();
    }

    @Override
    public StudentOption getStudentCountData() {
        List<Map<String, Object>> mapList = studentMapper.countStudentCountData();
        List<String> clazzNames = new ArrayList<>();
        List<Integer> studentCounts = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            clazzNames.add((String) map.get("name"));
            Number num = (Number) map.get("num");
            studentCounts.add(num.intValue());
        }

        return new StudentOption(clazzNames, studentCounts);

    }

    @Override
    public List<Map<String, Object>> getDegreeOption() {
        return studentMapper.countDegreeOption();
    }
}
