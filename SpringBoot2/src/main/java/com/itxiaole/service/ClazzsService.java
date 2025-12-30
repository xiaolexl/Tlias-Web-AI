package com.itxiaole.service;

import com.itxiaole.pojo.Clazz;
import com.itxiaole.pojo.ClazzsQueryParam;
import com.itxiaole.pojo.PageResult;

import java.util.List;

public interface ClazzsService {
    PageResult<Clazz> page(ClazzsQueryParam param);
    void add(Clazz clazz);
    Clazz getValueById(Integer id);
    void update(Clazz clazz);
    void deleteById(Integer id);
    List<Clazz> list();

}
