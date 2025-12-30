package com.itxiaole.service;

import com.itxiaole.pojo.PageResult;
import com.itxiaole.pojo.Student;
import com.itxiaole.pojo.StudentQueryParm;

public interface StudentService {
    PageResult<Student> page(StudentQueryParm sq);
    void save(Student student);
    void add(Student student);
    Student getById(Integer id);
    void delete(Integer ids);
    void vio(Integer id, Integer score);
}
