package com.intership.intershipTHS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private long id;
    private String name;
    private int age;
    private DepartmentDto department;
    private List<MobileNumberDto> mobileNumbers;
}
