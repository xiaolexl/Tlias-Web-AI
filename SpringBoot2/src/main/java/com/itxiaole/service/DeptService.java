package com.itxiaole.service;

import com.itxiaole.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> getDepts();
    void deleteDepts(Integer id);
    void addDepts(Dept dept);
    Dept getById(Integer id);
    void updateDepts(Dept dept);
}
