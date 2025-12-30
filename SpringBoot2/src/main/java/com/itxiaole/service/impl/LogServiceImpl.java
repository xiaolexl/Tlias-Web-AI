package com.itxiaole.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaole.mapper.EmpMapper;
import com.itxiaole.mapper.OperateLogMapper;
import com.itxiaole.pojo.Emp;
import com.itxiaole.pojo.OperateLog;
import com.itxiaole.pojo.PageResult;
import com.itxiaole.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.LogManager;


@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    OperateLogMapper mapper;

    @Autowired
    EmpMapper empMapper;

    @Override
    public PageResult<OperateLog> page(Integer page,Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Page<OperateLog> p = (Page<OperateLog>) mapper.list();
        for (OperateLog log:p){
            log.setOperateEmpName(empMapper.getNameById(log.getOperateEmpId()));
        }
        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
