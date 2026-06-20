CREATE DATABASE IF NOT EXISTS mybatis_demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE mybatis_demo;

DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS clazz;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE teacher (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT,
    title VARCHAR(30),
    dept_id BIGINT,
    CONSTRAINT fk_teacher_dept FOREIGN KEY (dept_id) REFERENCES department(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE clazz (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    grade INT,
    head_teacher_id BIGINT,
    CONSTRAINT fk_clazz_head_teacher FOREIGN KEY (head_teacher_id) REFERENCES teacher(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    hours INT,
    teacher_id BIGINT,
    class_id BIGINT,
    CONSTRAINT fk_course_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    CONSTRAINT fk_course_class FOREIGN KEY (class_id) REFERENCES clazz(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO department (name, location) VALUES ('计算机学院', '教学楼A');
INSERT INTO department (name, location) VALUES ('数学学院', '教学楼B');
INSERT INTO department (name, location) VALUES ('外国语学院', '教学楼C');

INSERT INTO teacher (name, age, title, dept_id) VALUES ('张三', 35, '教授', 1);
INSERT INTO teacher (name, age, title, dept_id) VALUES ('李四', 28, '讲师', 1);
INSERT INTO teacher (name, age, title, dept_id) VALUES ('王五', 42, '副教授', 1);

INSERT INTO teacher (name, age, title, dept_id) VALUES ('赵六', 30, '讲师', 2);
INSERT INTO teacher (name, age, title, dept_id) VALUES ('钱七', 45, '教授', 2);
INSERT INTO teacher (name, age, title, dept_id) VALUES ('孙八', 33, '副教授', 2);

INSERT INTO teacher (name, age, title, dept_id) VALUES ('周九', 29, '讲师', 3);
INSERT INTO teacher (name, age, title, dept_id) VALUES ('吴十', 38, '副教授', 3);

INSERT INTO teacher (name, age, title, dept_id) VALUES ('无部门', 50, '教授', NULL);

INSERT INTO clazz (name, grade, head_teacher_id) VALUES ('计算机一班', 2023, 1);
INSERT INTO clazz (name, grade, head_teacher_id) VALUES ('计算机二班', 2023, 2);
INSERT INTO clazz (name, grade, head_teacher_id) VALUES ('数学一班', 2024, 5);
INSERT INTO clazz (name, grade, head_teacher_id) VALUES ('外语一班', 2024, 7);

INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('Java程序设计', 64, 1, 1);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('数据结构', 48, 2, 1);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('操作系统', 56, 3, 1);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('计算机网络', 48, 1, 2);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('数据库原理', 64, 3, 2);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('高等数学', 80, 5, 3);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('线性代数', 48, 4, 3);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('大学英语', 64, 7, 4);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('英语口语', 32, 8, 4);
INSERT INTO course (name, hours, teacher_id, class_id) VALUES ('Python编程', 48, 2, 2);

DROP TABLE IF EXISTS score;

CREATE TABLE score (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(50) NOT NULL,
    course_id BIGINT,
    score DECIMAL(5,1),
    semester VARCHAR(20),
    CONSTRAINT fk_score_course FOREIGN KEY (course_id) REFERENCES course(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO score (student_name, course_id, score, semester) VALUES ('李明', 1, 85.5, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('王芳', 1, 92.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('张华', 1, 78.5, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('刘强', 1, 58.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('陈静', 1, NULL, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('赵伟', 1, 88.0, '2024-2025-1');

INSERT INTO score (student_name, course_id, score, semester) VALUES ('李明', 2, 76.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('王芳', 2, 89.5, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('张华', 2, NULL, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('刘强', 2, 65.5, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('陈静', 2, 95.0, '2024-2025-1');

INSERT INTO score (student_name, course_id, score, semester) VALUES ('李明', 6, 88.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('王芳', 6, 91.5, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('张华', 6, 72.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('刘强', 6, NULL, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('陈静', 6, 68.5, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('赵伟', 6, 55.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('孙丽', 6, 82.0, '2024-2025-1');

INSERT INTO score (student_name, course_id, score, semester) VALUES ('周杰', 8, 79.5, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('吴敏', 8, 85.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('郑涛', 8, NULL, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('冯雪', 8, 93.0, '2024-2025-1');
INSERT INTO score (student_name, course_id, score, semester) VALUES ('许强', 8, 67.5, '2024-2025-1');
