package com.intership.intershipTHS.mapper;

import com.intership.intershipTHS.dto.TeacherDto;
import com.intership.intershipTHS.entity.Teacher;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper {

    TeacherDto maptoTeacherDto(Teacher teacher);
    @InheritInverseConfiguration
    Teacher maptoTeacher(TeacherDto teacherDto);


    List<TeacherDto> maptoTeacherDtos(List<Teacher>teachers);
    List<Teacher>maptoTeachers(List<TeacherDto>teacherDtos);



}
