package com.intership.intershipTHS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private long id;
    private String name;
    private int age;
    private DepartmentDto department;
    private List<MobileNumberDto> mobileNumbers;
    private Set<TeacherDto> teachers = new HashSet<>();
}
