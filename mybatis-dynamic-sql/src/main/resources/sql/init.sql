CREATE DATABASE IF NOT EXISTS mybatis_demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE mybatis_demo;

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
