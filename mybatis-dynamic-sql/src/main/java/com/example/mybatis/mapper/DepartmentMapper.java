package com.example.mybatis.mapper;

import com.example.mybatis.entity.Department;

public interface DepartmentMapper {

    Department selectDeptWithTeachersById(Long deptId);
}
