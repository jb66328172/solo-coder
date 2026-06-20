package com.example.mybatis.entity;

public class Teacher {
    private Long id;
    private String name;
    private Integer age;
    private String title;
    private Long deptId;

    public Teacher() {
    }

    public Teacher(Long id, String name, Integer age, String title, Long deptId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.title = title;
        this.deptId = deptId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name='" + name + '\'' + ", age=" + age + ", title='" + title + '\'' + ", deptId=" + deptId + '}';
    }
}
