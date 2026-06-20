package com.example.mybatis;

import com.example.mybatis.entity.Department;
import com.example.mybatis.entity.Teacher;
import com.example.mybatis.mapper.DepartmentMapper;
import com.example.mybatis.mapper.TeacherMapper;
import com.example.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TestMybatis {

    private SqlSession sqlSession;
    private TeacherMapper teacherMapper;
    private DepartmentMapper departmentMapper;

    @Before
    public void setUp() {
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
    }

    @After
    public void tearDown() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByDeptAndAgeRange() {
        System.out.println("===== 1. 多参数查询：根据部门ID和年龄范围查询教师 =====");
        List<Teacher> teachers = teacherMapper.selectByDeptAndAgeRange(1L, 25, 40);
        System.out.println("部门ID=1，年龄25-40之间的教师：");
        for (Teacher t : teachers) {
            System.out.println(t);
        }
        System.out.println("共查询到 " + teachers.size() + " 条记录\n");
    }

    @Test
    public void testSelectByCondition() {
        System.out.println("===== 2. 动态SQL查询：条件组合查询 =====");

        System.out.println("--- 按姓名模糊查询 ---");
        Teacher condition1 = new Teacher();
        condition1.setName("张");
        List<Teacher> list1 = teacherMapper.selectByCondition(condition1);
        for (Teacher t : list1) {
            System.out.println(t);
        }

        System.out.println("\n--- 按部门ID查询 ---");
        Teacher condition2 = new Teacher();
        condition2.setDeptId(2L);
        List<Teacher> list2 = teacherMapper.selectByCondition(condition2);
        for (Teacher t : list2) {
            System.out.println(t);
        }

        System.out.println("\n--- 按职称查询 ---");
        Teacher condition3 = new Teacher();
        condition3.setTitle("教授");
        List<Teacher> list3 = teacherMapper.selectByCondition(condition3);
        for (Teacher t : list3) {
            System.out.println(t);
        }

        System.out.println("\n--- 姓名模糊 + 部门ID组合查询 ---");
        Teacher condition4 = new Teacher();
        condition4.setName("李");
        condition4.setDeptId(1L);
        List<Teacher> list4 = teacherMapper.selectByCondition(condition4);
        for (Teacher t : list4) {
            System.out.println(t);
        }

        System.out.println("\n--- 姓名模糊 + 部门ID + 职称组合查询 ---");
        Teacher condition5 = new Teacher();
        condition5.setName("王");
        condition5.setDeptId(1L);
        condition5.setTitle("副教授");
        List<Teacher> list5 = teacherMapper.selectByCondition(condition5);
        for (Teacher t : list5) {
            System.out.println(t);
        }

        System.out.println("\n--- 无条件查询（全部条件为空，应返回空结果） ---");
        Teacher condition6 = new Teacher();
        List<Teacher> list6 = teacherMapper.selectByCondition(condition6);
        System.out.println("共查询到 " + list6.size() + " 条记录");
        assertTrue("三条件全空时应返回空结果", list6.isEmpty());
        System.out.println();
    }

    @Test
    public void testSelectByConditionAllEmpty() {
        System.out.println("===== 边界测试：动态SQL三条件全空 =====");

        System.out.println("--- 所有属性都为null ---");
        Teacher condition1 = new Teacher();
        List<Teacher> list1 = teacherMapper.selectByCondition(condition1);
        System.out.println("所有属性为null，查询结果数：" + list1.size());
        assertTrue("所有属性为null时应返回空结果", list1.isEmpty());

        System.out.println("\n--- name为空字符串，其他为null ---");
        Teacher condition2 = new Teacher();
        condition2.setName("");
        List<Teacher> list2 = teacherMapper.selectByCondition(condition2);
        System.out.println("name为空字符串，查询结果数：" + list2.size());
        assertTrue("name为空字符串时应返回空结果", list2.isEmpty());

        System.out.println("\n--- title为空字符串，其他为null ---");
        Teacher condition3 = new Teacher();
        condition3.setTitle("");
        List<Teacher> list3 = teacherMapper.selectByCondition(condition3);
        System.out.println("title为空字符串，查询结果数：" + list3.size());
        assertTrue("title为空字符串时应返回空结果", list3.isEmpty());

        System.out.println("\n--- name和title都为空字符串，deptId为null ---");
        Teacher condition4 = new Teacher();
        condition4.setName("");
        condition4.setTitle("");
        List<Teacher> list4 = teacherMapper.selectByCondition(condition4);
        System.out.println("name和title都为空字符串，查询结果数：" + list4.size());
        assertTrue("三条件全空时应返回空结果", list4.isEmpty());

        System.out.println();
    }

    @Test
    public void testDeleteBatchByIds() {
        System.out.println("===== 3. 批量删除：根据ID列表批量删除教师 =====");
        List<Long> ids = Arrays.asList(8L, 9L);
        int rows = teacherMapper.deleteBatchByIds(ids);
        System.out.println("批量删除ID为8、9的教师，影响行数：" + rows + "\n");
    }

    @Test
    public void testDeleteBatchByIdsEmptyList() {
        System.out.println("===== 边界测试：批量删除空list =====");

        System.out.println("--- 空list删除 ---");
        List<Long> emptyIds = Collections.emptyList();
        int rows1 = teacherMapper.deleteBatchByIds(emptyIds);
        System.out.println("空list批量删除，影响行数：" + rows1);
        assertEquals("空list删除应影响0行", 0, rows1);

        System.out.println("\n--- new ArrayList() 空列表删除 ---");
        List<Long> emptyArrayList = new ArrayList<>();
        int rows2 = teacherMapper.deleteBatchByIds(emptyArrayList);
        System.out.println("new ArrayList()批量删除，影响行数：" + rows2);
        assertEquals("空ArrayList删除应影响0行", 0, rows2);

        System.out.println();
    }

    @Test
    public void testDeleteBatchByIdsNullList() {
        System.out.println("===== 边界测试：批量删除null list =====");

        int rows = teacherMapper.deleteBatchByIds(null);
        System.out.println("null list批量删除，影响行数：" + rows);
        assertEquals("null list删除应影响0行", 0, rows);

        System.out.println();
    }

    @Test
    public void testSelectDeptWithTeachersById() {
        System.out.println("===== 4. 关联查询：查询部门及该部门所有教师（一对多） =====");
        Department dept = departmentMapper.selectDeptWithTeachersById(1L);
        System.out.println("部门名称：" + dept.getName());
        System.out.println("部门位置：" + dept.getLocation());
        System.out.println("该部门下的教师列表：");
        List<Teacher> teachers = dept.getTeachers();
        if (teachers != null) {
            for (Teacher t : teachers) {
                System.out.println("  - " + t);
            }
        } else {
            System.out.println("  暂无教师");
        }
        System.out.println();

        Department dept2 = departmentMapper.selectDeptWithTeachersById(2L);
        System.out.println("部门名称：" + dept2.getName());
        System.out.println("部门位置：" + dept2.getLocation());
        System.out.println("该部门下的教师列表：");
        List<Teacher> teachers2 = dept2.getTeachers();
        if (teachers2 != null) {
            for (Teacher t : teachers2) {
                System.out.println("  - " + t);
            }
        } else {
            System.out.println("  暂无教师");
        }
    }
}
