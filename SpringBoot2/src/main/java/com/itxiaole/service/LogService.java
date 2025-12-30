package com.itxiaole.service;

import com.itxiaole.pojo.OperateLog;
import com.itxiaole.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(Integer page,Integer pageSize);
}
