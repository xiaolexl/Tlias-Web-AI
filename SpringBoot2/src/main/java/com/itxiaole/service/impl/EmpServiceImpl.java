package com.itxiaole.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxiaole.mapper.EmpExprMapper;
import com.itxiaole.mapper.EmpLogMapper;
import com.itxiaole.mapper.EmpMapper;
import com.itxiaole.pojo.*;
import com.itxiaole.service.EmpLogService;
import com.itxiaole.service.EmpService;
import com.itxiaole.untils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Resource
    private EmpMapper EmpMapper;
    @Resource
    private EmpExprMapper EmpExprMapper;
    @Resource
    private EmpLogMapper EmpLogMapper;
    @Resource
    private EmpLogService EmpLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam e) {
        PageHelper.startPage(e.getPage(), e.getPageSize());
        Page<Emp> p = (Page<Emp>) EmpMapper.list(e);
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp e) {

        try{
            e.setCreateTime(LocalDateTime.now());
            e.setUpdateTime(LocalDateTime.now());
            EmpMapper.insert(e);

            List<EmpExpr> exprList = e.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(e1->{
                    e1.setEmpId(e.getId());
                });
                EmpExprMapper.insertBatch(exprList);
            }
        }finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工" + e);
            EmpLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        EmpMapper.deleteByIds(ids);
        EmpExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp selectAllById(Integer id) {
        Emp emps = EmpMapper.selectAllById(id);
        return emps;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        EmpMapper.updateById(emp);
        EmpExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            for(EmpExpr expr:exprList){
                expr.setEmpId(emp.getId());
            }
            EmpExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> selectAll() {
        return EmpMapper.selectAll();
    }

    @Override
    public LoginInfo login(Emp emp) {

        Emp e = EmpMapper.login(emp);

        if(e!=null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),token);
        }

        return null;
    }

}
