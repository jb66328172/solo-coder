package com.example.mybatis.entity;

import java.math.BigDecimal;

public class Score {
    private Long id;
    private String studentName;
    private Long courseId;
    private BigDecimal score;
    private String semester;
    private Course course;

    public Score() {
    }

    public Score(Long id, String studentName, Long courseId, BigDecimal score, String semester) {
        this.id = id;
        this.studentName = studentName;
        this.courseId = courseId;
        this.score = score;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Score{id=" + id + ", studentName='" + studentName + '\'' + ", courseId=" + courseId + ", score=" + score + ", semester='" + semester + '\'' + '}';
    }
}
