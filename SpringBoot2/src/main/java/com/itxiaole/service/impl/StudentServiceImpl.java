package com.itxiaole.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaole.mapper.ClazzsMapper;
import com.itxiaole.mapper.StudentMapper;
import com.itxiaole.pojo.PageResult;
import com.itxiaole.pojo.Result;
import com.itxiaole.pojo.Student;
import com.itxiaole.pojo.StudentQueryParm;
import com.itxiaole.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentMapper mapper;

    @Autowired
    private ClazzsMapper clazzsMapper;

    @Override
    public PageResult<Student> page(StudentQueryParm sq) {
        PageHelper.startPage(sq.getPage(),sq.getPageSize());
        Page<Student> p = (Page<Student>) mapper.list(sq);
        for(Student s: p){
            s.setClazzName(clazzsMapper.getValueById(s.getClazzId()).getName());
        }
        return new PageResult<>(p.getTotal(),p);
    }

    @Override
    public void save(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        mapper.update(student);
        return;
    }

    @Override
    public void add(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        student.setCreateTime(LocalDateTime.now());
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        mapper.insert(student);
        return;
    }

    @Override
    public Student getById(Integer id) {
        return mapper.getById(id);
    }

    @Override
    public void delete(Integer ids) {
        mapper.delete(ids);
        return;
    }

    @Override
    public void vio(Integer id, Integer score) {
        mapper.vio(id,score);
        return;
    }
}
