package com.itxiaole.mapper;

import com.itxiaole.pojo.Student;
import com.itxiaole.pojo.StudentQueryParm;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    @Select("SELECT count(*) FROM student WHERE clazz_id = #{clazzId}")
    Long countByClazzId(Integer clazzId);

    List<Student> list(StudentQueryParm parm);


    @Update("UPDATE student SET " +
            "name = #{name}, " +
            "no = #{no}, " +
            "gender = #{gender}, " +
            "phone = #{phone}, " +
            "id_card = #{idCard}, " +          // 注意：数据库是 id_card，Java是 idCard
            "is_college = #{isCollege}, " +    // 注意：数据库是 is_college
            "address = #{address}, " +
            "degree = #{degree}, " +
            "graduation_date = #{graduationDate}, " + // 注意：数据库是 graduation_date
            "clazz_id = #{clazzId}, " +        // 注意：数据库是 clazz_id
            "violation_count = #{violationCount}, " +
            "violation_score = #{violationScore}, " +
            "update_time = #{updateTime} " +   // 记得更新修改时间
            "WHERE id = #{id}")
    void update(Student student);

    @Insert("INSERT INTO student(name, no, gender, phone, id_card, is_college, " +
            "address, degree, graduation_date, clazz_id, violation_count, " +
            "violation_score, create_time, update_time) " +
            "VALUES (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, " +
            "#{address}, #{degree}, #{graduationDate}, #{clazzId}, #{violationCount}, " +
            "#{violationScore}, #{createTime}, #{updateTime})")
    void insert(Student student);

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student getById(Integer id);

    @Delete("delete from student where id = #{ids}")
    void delete(Integer ids);

    @Update("UPDATE student SET " +
            "violation_count = violation_count + 1, " +        // 次数 + 1
            "violation_score = violation_score + #{score}, " + // 分数 + 传入的分数
            "update_time = now() " +                           // 顺便更新修改时间
            "WHERE id = #{id}")
    void vio(@Param("id") Integer id, @Param("score") Integer score);

    @Select("SELECT c.name, COUNT(s.id) AS num " +
            "FROM clazz c " +
            "LEFT JOIN student s ON c.id = s.clazz_id " +
            "GROUP BY c.name")
    List<Map<String, Object>> countStudentCountData();

    @Select("SELECT " +
            " CASE degree " +
            "   WHEN 1 THEN '初中' " +
            "   WHEN 2 THEN '高中' " +
            "   WHEN 3 THEN '大专' " +
            "   WHEN 4 THEN '本科' " +
            "   WHEN 5 THEN '硕士' " +
            "   WHEN 6 THEN '博士' " +
            "   ELSE '未知' " +
            " END AS name, " +
            " COUNT(*) AS value " +
            "FROM student " +
            "GROUP BY degree")
    List<Map<String, Object>> countDegreeOption();
}
