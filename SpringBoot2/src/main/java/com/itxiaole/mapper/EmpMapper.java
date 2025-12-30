package com.itxiaole.mapper;

import com.itxiaole.pojo.Emp;
import com.itxiaole.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    List<Emp> list(EmpQueryParam e);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "VALUE (#{username}, #{name} ,#{gender} ,#{phone} , #{job},#{salary}, #{image} , #{entryDate}, #{deptId}, #{createTime} ,#{updateTime} );")
    void insert(Emp e);

    void deleteByIds(List<Integer> ids);

    Emp selectAllById(Integer ids);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @Select("select if(gender = 1,'男性','女性') name  , count(*) value from emp group by gender")
    List<Map<String, Integer>> countEmpGenderData();

    @Select("select name from emp where id = #{id}")
    String getNameById(Integer id);

    @Select("select * from emp e")
    List<Emp> selectAll();

    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp login(Emp emp);

}
