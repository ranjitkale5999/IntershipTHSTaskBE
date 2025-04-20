package com.intership.intershipTHS.mapper;

import com.intership.intershipTHS.dto.DepartmentDto;
import com.intership.intershipTHS.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

    DepartmentDto mapDepartmentDto(Department department);

    Department maDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> maptoDepartmentDtos(List<Department>departments);
    List<Department> maptoDepartments(List<DepartmentDto> departmentDtos);

}

