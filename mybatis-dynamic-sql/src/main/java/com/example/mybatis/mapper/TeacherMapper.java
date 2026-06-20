package com.example.mybatis.mapper;

import com.example.mybatis.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {

    List<Teacher> selectByDeptAndAgeRange(@Param("deptId") Long deptId, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);

    List<Teacher> selectByCondition(Teacher teacher);

    int deleteBatchByIds(@Param("ids") List<Long> ids);
}
