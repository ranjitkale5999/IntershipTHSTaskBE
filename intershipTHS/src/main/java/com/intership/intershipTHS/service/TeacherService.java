package com.intership.intershipTHS.service;

import com.intership.intershipTHS.dto.TeacherDto;
import com.intership.intershipTHS.entity.Teacher;
import com.intership.intershipTHS.mapper.TeacherMapper;
import com.intership.intershipTHS.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;


    @Autowired
    private TeacherMapper teacherMapper;


    public TeacherDto addTeacher(TeacherDto teacherDto){

        Teacher teacher=teacherMapper.maptoTeacher(teacherDto);

        Teacher saveTeacher=teacherRepo.save(teacher);


        return teacherMapper.maptoTeacherDto(saveTeacher);
    }


    public List<TeacherDto> getAllTeacher(){
        List<Teacher> teachers= teacherRepo.findAll();
        return teacherMapper.maptoTeacherDtos(teachers);
    }


}