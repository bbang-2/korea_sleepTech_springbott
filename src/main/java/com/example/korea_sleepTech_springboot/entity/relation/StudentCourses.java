package com.example.korea_sleepTech_springboot.entity.relation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class StudentCourses {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrolledDate;
    private int grade;
}
/*
        CREATE TABLE student_courses (
        student_id BIGINT PRIMARY KEY AUTO_INCREMENT,
        course_id BIGINT,

        enrolled_date DATETIME,
        grade INT

        FOREIGN KEY (student_id) REFERENCES student(id),
        FOREIGN KEY (course_id) REFERENCES course(id)
    );
 */