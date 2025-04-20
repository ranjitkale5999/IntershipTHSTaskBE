package com.intership.intershipTHS.service;

import com.intership.intershipTHS.dto.DepartmentDto;
import com.intership.intershipTHS.entity.Department;
import com.intership.intershipTHS.mapper.DepartmentMapper;
import com.intership.intershipTHS.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentDto addDepartment (DepartmentDto departmentDto){

        Department department=departmentMapper.maDepartment(departmentDto);
        Department departmentResponse= departmentRepo.save(department);

        DepartmentDto reponse=departmentMapper.mapDepartmentDto(departmentResponse);
        return reponse;
    }

    public List<DepartmentDto> getAllDepartment(){
        List<Department> departments=departmentRepo.findAll();
        return departmentMapper.maptoDepartmentDtos(departments);
    }


}
