package com.intership.intershipTHS.service;

import com.intership.intershipTHS.Exceptions.InvalidInputException;
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


    public TeacherDto addTeacher(TeacherDto teacherDto) {

        Teacher teacher = teacherMapper.maptoTeacher(teacherDto);

        Teacher saveTeacher = teacherRepo.save(teacher);


        return teacherMapper.maptoTeacherDto(saveTeacher);
    }

    public TeacherDto getByIdTeachers(long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new InvalidInputException("Teacher not found with ID :-" + id));
        return teacherMapper.maptoTeacherDto(teacher);
    }

    public List<TeacherDto> getAllTeacher() {
//        List<Teacher> teachers = new ArrayList<>();
        List<Teacher> teachers = teacherRepo.findAll();
        if (teachers.isEmpty()) {
            throw new InvalidInputException("Teachers not found");
        }
        return teacherMapper.maptoTeacherDtos(teachers);
    }


    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new InvalidInputException("Teacher not found with ID: " + id));

        teacherRepo.delete(teacher);
    }


    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new InvalidInputException("Teacher not found with ID:- " + id));

        teacher.setName(teacherDto.getName());
        teacher.setSubject(teacherDto.getSubject());

        Teacher updateTeacher = teacherRepo.save(teacher);
        return teacherMapper.maptoTeacherDto(updateTeacher);
    }

}