package com.intership.intershipTHS.mapper;

import com.intership.intershipTHS.dto.StudentDto;
import com.intership.intershipTHS.entity.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "studentName", target = "name"),
    })
    StudentDto maptoStudentDto(Student student);

    @Mappings({@Mapping(source = "name", target ="studentName" ),
    })
    @InheritInverseConfiguration
    Student maptoStudent(StudentDto studentDto);

    List<StudentDto> maptoStudentDtos(List<Student> students);
    List<Student> maptoStudent (List<StudentDto> studentDtos);
}

