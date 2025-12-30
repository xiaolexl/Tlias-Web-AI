package com.itxiaole.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaole.exception.BusinessException;
import com.itxiaole.mapper.ClazzsMapper;
import com.itxiaole.mapper.EmpMapper;
import com.itxiaole.mapper.StudentMapper;
import com.itxiaole.pojo.Clazz;
import com.itxiaole.pojo.ClazzsQueryParam;
import com.itxiaole.pojo.Emp;
import com.itxiaole.pojo.PageResult;
import com.itxiaole.service.ClazzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzsServiceImpl implements ClazzsService {

    @Autowired
    ClazzsMapper mapper;

    @Autowired
    EmpMapper empMapper;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzsQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        Page<Clazz> p = (Page<Clazz>) mapper.list(param);
        for (Clazz clazz : p) {
            clazz.setMasterName(empMapper.getNameById(clazz.getMasterId()));
            if (LocalDate.now().isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else if (LocalDate.now().isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读");
            }
        }
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        mapper.add(clazz);
    }

    @Override
    public Clazz getValueById(Integer id) {
        return mapper.getValueById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        mapper.update(clazz);
        return;
    }

    @Override
    public void deleteById(Integer id) {
        Long count = studentMapper.countByClazzId(id);
        if(count>0){
            throw new BusinessException("对不起，该班级学生数不为0，无法删除");
        }
        mapper.deleteById(id);
        return;
    }

    @Override
    public List<Clazz> list() {
        return mapper.list(new ClazzsQueryParam());
    }
}
