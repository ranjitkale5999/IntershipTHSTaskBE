package com.intership.intershipTHS.service;

import com.intership.intershipTHS.dto.StudentDto;
import com.intership.intershipTHS.entity.Student;
import com.intership.intershipTHS.mapper.StudentMapper;
import com.intership.intershipTHS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public StudentDto addStudent(StudentDto studentDto) throws Exception {
        System.out.println("Received studentDto: " + studentDto);
        Student student = studentMapper.maptoStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.maptoStudentDto(savedStudent);
    }

    public List<StudentDto> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.maptoStudentDtos(students);
    }

    public StudentDto getStudentById(long id) throws AttributeNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Student not found with id " + id));

        return studentMapper.maptoStudentDto(student); // Mapping entity to DTO
    }

    public boolean deleteStudent(Long id) throws AttributeNotFoundException {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return true; // Student was found and deleted
        } else {
            throw new AttributeNotFoundException("Student not found with ID: " + id);
        }
    }

    @Transactional
    public StudentDto
    updateStudent(Long id, StudentDto studentDto) throws AttributeNotFoundException {
        List<String> errorMessages = new ArrayList<>();
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Student not found with id " + id));
        student.setStudentName(studentDto.getName());
        student.setAge(studentDto.getAge());
                Student updatedStudent = studentRepository.save(student);
        return studentMapper.maptoStudentDto(updatedStudent);
    }


}
