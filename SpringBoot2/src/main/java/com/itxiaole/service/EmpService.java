package com.itxiaole.service;


import com.itxiaole.pojo.Emp;
import com.itxiaole.pojo.EmpQueryParam;
import com.itxiaole.pojo.LoginInfo;
import com.itxiaole.pojo.PageResult;

import java.util.List;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam e);
    void save(Emp e);
    void delete(List<Integer> ids);

    Emp selectAllById(Integer id);

    void update(Emp emp);

    List<Emp> selectAll();

    LoginInfo login(Emp emp);
}
