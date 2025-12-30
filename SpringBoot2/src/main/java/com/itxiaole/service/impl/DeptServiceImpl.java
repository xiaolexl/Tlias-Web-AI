package com.itxiaole.service.impl;

import com.itxiaole.mapper.DeptMapper;
import com.itxiaole.pojo.Dept;
import com.itxiaole.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    DeptMapper mapper;

    public List<Dept> getDepts() {
        List<Dept> depts = mapper.getDepts();
        return depts;
    }
    public void deleteDepts(Integer id){
        mapper.deleteDepts(id);
        return;
    }
    public void addDepts(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        mapper.addDepts(dept);
        return;
    }

    @Override
    public Dept getById(Integer id) {
        return mapper.getById(id);
    }

    @Override
    public void updateDepts(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        mapper.updateDepts(dept);
        return;
    }


}
