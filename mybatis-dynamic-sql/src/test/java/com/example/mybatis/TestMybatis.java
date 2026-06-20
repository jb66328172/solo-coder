package com.example.mybatis;

import com.example.mybatis.entity.Clazz;
import com.example.mybatis.entity.Course;
import com.example.mybatis.entity.Department;
import com.example.mybatis.entity.Teacher;
import com.example.mybatis.mapper.ClazzMapper;
import com.example.mybatis.mapper.CourseMapper;
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
    private ClazzMapper clazzMapper;
    private CourseMapper courseMapper;

    @Before
    public void setUp() {
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        clazzMapper = sqlSession.getMapper(ClazzMapper.class);
        courseMapper = sqlSession.getMapper(CourseMapper.class);
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

    @Test
    public void testSelectClazzWithDetailById() {
        System.out.println("===== 5. 关联查询：根据班级ID查询班级详情（含班主任、所有课程及授课老师） =====");

        System.out.println("--- 查询班级ID=1（计算机一班） ---");
        Clazz clazz1 = clazzMapper.selectClazzWithDetailById(1L);
        assertNotNull("班级不应为null", clazz1);
        System.out.println("班级名称：" + clazz1.getName());
        System.out.println("年级：" + clazz1.getGrade());
        Teacher headTeacher = clazz1.getHeadTeacher();
        if (headTeacher != null) {
            System.out.println("班主任：" + headTeacher.getName() + "（" + headTeacher.getTitle() + "）");
        } else {
            System.out.println("班主任：暂无");
        }
        List<Course> courses = clazz1.getCourses();
        System.out.println("该班级开设的课程：");
        if (courses != null && !courses.isEmpty()) {
            for (Course c : courses) {
                String teacherName = (c.getTeacher() != null) ? c.getTeacher().getName() : "暂无";
                System.out.println("  - " + c.getName() + "（" + c.getHours() + "学时，授课老师：" + teacherName + "）");
            }
            assertTrue("计算机一班应有3门课程", courses.size() >= 3);
        } else {
            System.out.println("  暂无课程");
        }
        System.out.println();

        System.out.println("--- 查询班级ID=4（外语一班） ---");
        Clazz clazz4 = clazzMapper.selectClazzWithDetailById(4L);
        assertNotNull("班级不应为null", clazz4);
        System.out.println("班级名称：" + clazz4.getName());
        System.out.println("年级：" + clazz4.getGrade());
        Teacher headTeacher4 = clazz4.getHeadTeacher();
        if (headTeacher4 != null) {
            System.out.println("班主任：" + headTeacher4.getName() + "（" + headTeacher4.getTitle() + "）");
        }
        List<Course> courses4 = clazz4.getCourses();
        System.out.println("该班级开设的课程：");
        if (courses4 != null && !courses4.isEmpty()) {
            for (Course c : courses4) {
                String teacherName = (c.getTeacher() != null) ? c.getTeacher().getName() : "暂无";
                System.out.println("  - " + c.getName() + "（" + c.getHours() + "学时，授课老师：" + teacherName + "）");
            }
        } else {
            System.out.println("  暂无课程");
        }
        System.out.println();
    }

    @Test
    public void testSelectAllWithHeadTeacher() {
        System.out.println("===== 6. 查询所有班级（带上班主任姓名） =====");
        List<Clazz> clazzList = clazzMapper.selectAllWithHeadTeacher();
        System.out.println("共查询到 " + clazzList.size() + " 个班级：");
        assertEquals("应有4个班级", 4, clazzList.size());
        for (Clazz c : clazzList) {
            String headTeacherName = (c.getHeadTeacher() != null) ? c.getHeadTeacher().getName() : "暂无";
            System.out.println("  - " + c.getName() + "（" + c.getGrade() + "级，班主任：" + headTeacherName + "）");
            assertNotNull("班主任对象不应为null", c.getHeadTeacher());
            assertNotNull("班主任姓名不应为null", c.getHeadTeacher().getName());
        }
        System.out.println();
    }

    @Test
    public void testSelectCourseWithDetailById() {
        System.out.println("===== 7. 关联查询：根据课程ID查询课程详情（含授课老师和所属班级） =====");

        System.out.println("--- 查询课程ID=1（Java程序设计） ---");
        Course course1 = courseMapper.selectCourseWithDetailById(1L);
        assertNotNull("课程不应为null", course1);
        System.out.println("课程名称：" + course1.getName());
        System.out.println("学时：" + course1.getHours());
        Teacher teacher = course1.getTeacher();
        if (teacher != null) {
            System.out.println("授课老师：" + teacher.getName() + "（" + teacher.getTitle() + "）");
            assertEquals("授课老师应为张三", "张三", teacher.getName());
        }
        Clazz clazz = course1.getClazz();
        if (clazz != null) {
            System.out.println("所属班级：" + clazz.getName() + "（" + clazz.getGrade() + "级）");
            assertEquals("所属班级应为计算机一班", "计算机一班", clazz.getName());
        }
        System.out.println();

        System.out.println("--- 查询课程ID=10（Python编程） ---");
        Course course10 = courseMapper.selectCourseWithDetailById(10L);
        assertNotNull("课程不应为null", course10);
        System.out.println("课程名称：" + course10.getName());
        System.out.println("学时：" + course10.getHours());
        Teacher teacher10 = course10.getTeacher();
        if (teacher10 != null) {
            System.out.println("授课老师：" + teacher10.getName() + "（" + teacher10.getTitle() + "）");
        }
        Clazz clazz10 = course10.getClazz();
        if (clazz10 != null) {
            System.out.println("所属班级：" + clazz10.getName() + "（" + clazz10.getGrade() + "级）");
        }
        System.out.println();
    }

    @Test
    public void testSelectCoursesByTeacherId() {
        System.out.println("===== 8. 按教师ID查询该教师所有课程 =====");

        System.out.println("--- 查询教师ID=1（张三）的所有课程 ---");
        List<Course> courses1 = courseMapper.selectByTeacherId(1L);
        System.out.println("张三老师共教授 " + courses1.size() + " 门课程：");
        assertEquals("张三应教2门课程", 2, courses1.size());
        for (Course c : courses1) {
            String className = (c.getClazz() != null) ? c.getClazz().getName() : "暂无";
            System.out.println("  - " + c.getName() + "（" + c.getHours() + "学时，班级：" + className + "）");
        }
        System.out.println();

        System.out.println("--- 查询教师ID=2（李四）的所有课程 ---");
        List<Course> courses2 = courseMapper.selectByTeacherId(2L);
        System.out.println("李四老师共教授 " + courses2.size() + " 门课程：");
        assertEquals("李四应教2门课程", 2, courses2.size());
        for (Course c : courses2) {
            String className = (c.getClazz() != null) ? c.getClazz().getName() : "暂无";
            System.out.println("  - " + c.getName() + "（" + c.getHours() + "学时，班级：" + className + "）");
        }
        System.out.println();

        System.out.println("--- 查询教师ID=9（无部门）的所有课程 ---");
        List<Course> courses9 = courseMapper.selectByTeacherId(9L);
        System.out.println("无部门教师共教授 " + courses9.size() + " 门课程：");
        assertTrue("无部门教师应无课程", courses9.isEmpty());
        System.out.println();
    }

    @Test
    public void testSelectTeacherWithCoursesById() {
        System.out.println("===== 9. 关联查询：根据教师ID查询教师及其所教课程列表 =====");

        System.out.println("--- 查询教师ID=1（张三） ---");
        Teacher teacher1 = teacherMapper.selectTeacherWithCoursesById(1L);
        assertNotNull("教师不应为null", teacher1);
        System.out.println("教师姓名：" + teacher1.getName());
        System.out.println("年龄：" + teacher1.getAge());
        System.out.println("职称：" + teacher1.getTitle());
        List<Course> courses1 = teacher1.getCourses();
        System.out.println("所教课程列表：");
        if (courses1 != null && !courses1.isEmpty()) {
            for (Course c : courses1) {
                String className = (c.getClazz() != null) ? c.getClazz().getName() : "暂无";
                System.out.println("  - " + c.getName() + "（" + c.getHours() + "学时，班级：" + className + "）");
            }
            assertEquals("张三应教2门课程", 2, courses1.size());
        } else {
            System.out.println("  暂无课程");
        }
        System.out.println();

        System.out.println("--- 查询教师ID=5（钱七） ---");
        Teacher teacher5 = teacherMapper.selectTeacherWithCoursesById(5L);
        assertNotNull("教师不应为null", teacher5);
        System.out.println("教师姓名：" + teacher5.getName());
        System.out.println("年龄：" + teacher5.getAge());
        System.out.println("职称：" + teacher5.getTitle());
        List<Course> courses5 = teacher5.getCourses();
        System.out.println("所教课程列表：");
        if (courses5 != null && !courses5.isEmpty()) {
            for (Course c : courses5) {
                String className = (c.getClazz() != null) ? c.getClazz().getName() : "暂无";
                System.out.println("  - " + c.getName() + "（" + c.getHours() + "学时，班级：" + className + "）");
            }
            assertEquals("钱七应教1门课程", 1, courses5.size());
        } else {
            System.out.println("  暂无课程");
        }
        System.out.println();

        System.out.println("--- 查询教师ID=9（无部门，无课程） ---");
        Teacher teacher9 = teacherMapper.selectTeacherWithCoursesById(9L);
        assertNotNull("教师不应为null", teacher9);
        System.out.println("教师姓名：" + teacher9.getName());
        List<Course> courses9 = teacher9.getCourses();
        System.out.println("所教课程数量：" + (courses9 != null ? courses9.size() : 0));
        System.out.println();
    }
}
