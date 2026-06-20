package com.example.mybatis.mapper;

import com.example.mybatis.entity.Course;

import java.util.List;

public interface CourseMapper {

    Course selectCourseWithDetailById(Long id);

    List<Course> selectByTeacherId(Long teacherId);
}
