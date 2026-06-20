package com.example.mybatis.mapper;

import com.example.mybatis.entity.Clazz;

import java.util.List;

public interface ClazzMapper {

    Clazz selectClazzWithDetailById(Long id);

    List<Clazz> selectAllWithHeadTeacher();
}
