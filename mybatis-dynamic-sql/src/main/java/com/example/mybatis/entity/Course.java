package com.example.mybatis.entity;

public class Course {
    private Long id;
    private String name;
    private Integer hours;
    private Long teacherId;
    private Long classId;
    private Teacher teacher;
    private Clazz clazz;

    public Course() {
    }

    public Course(Long id, String name, Integer hours, Long teacherId, Long classId) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.teacherId = teacherId;
        this.classId = classId;
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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + '\'' + ", hours=" + hours + ", teacherId=" + teacherId + ", classId=" + classId + '}';
    }
}
