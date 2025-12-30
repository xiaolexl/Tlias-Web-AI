package com.itxiaole.mapper;

import com.itxiaole.pojo.Clazz;
import com.itxiaole.pojo.ClazzsQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzsMapper {
    List<Clazz> list(ClazzsQueryParam param);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);

    @Select("SELECT * FROM clazz WHERE id = #{id}")
    Clazz getValueById(Integer id);

    @Update("UPDATE clazz SET name = #{name}, room = #{room}, " +
            "begin_date = #{beginDate}, end_date = #{endDate}, " +
            "master_id = #{masterId}, subject = #{subject}, " +
            "update_time = #{updateTime} " +
            "WHERE id = #{id}")
    void update(Clazz clazz);

    @Delete("DELETE FROM clazz WHERE id = #{id}")
    void deleteById(Integer id);
}
