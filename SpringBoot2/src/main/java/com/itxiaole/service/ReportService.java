package com.itxiaole.service;

import com.itxiaole.pojo.JobOption;
import com.itxiaole.pojo.StudentOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();
    List<Map<String, Integer>> getEmpGenderData();
    StudentOption getStudentCountData();
    List<Map<String, Object>> getDegreeOption();
}
