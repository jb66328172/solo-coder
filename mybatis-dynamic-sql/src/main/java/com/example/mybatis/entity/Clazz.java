package com.example.mybatis.entity;

import java.util.List;

public class Clazz {
    private Long id;
    private String name;
    private Integer grade;
    private Long headTeacherId;
    private Teacher headTeacher;
    private List<Course> courses;

    public Clazz() {
    }

    public Clazz(Long id, String name, Integer grade, Long headTeacherId) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.headTeacherId = headTeacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getHeadTeacherId() {
        return headTeacherId;
    }

    public void setHeadTeacherId(Long headTeacherId) {
        this.headTeacherId = headTeacherId;
    }

    public Teacher getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(Teacher headTeacher) {
        this.headTeacher = headTeacher;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Clazz{id=" + id + ", name='" + name + '\'' + ", grade=" + grade + ", headTeacherId=" + headTeacherId + '}';
    }
}
