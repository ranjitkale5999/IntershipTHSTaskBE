package com.intership.intershipTHS.repository;

import com.intership.intershipTHS.entity.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> findStudentsByCustomCriteria(String area, String city);
}
