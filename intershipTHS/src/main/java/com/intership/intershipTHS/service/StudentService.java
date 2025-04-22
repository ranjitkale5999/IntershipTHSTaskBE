package com.intership.intershipTHS.service;

import com.intership.intershipTHS.dto.MobileNumberDto;
import com.intership.intershipTHS.dto.StudentDto;
import com.intership.intershipTHS.entity.Department;
import com.intership.intershipTHS.entity.MobileNumber;
import com.intership.intershipTHS.entity.Student;
import com.intership.intershipTHS.entity.Teacher;
import com.intership.intershipTHS.mapper.StudentMapper;
import com.intership.intershipTHS.repository.DepartmentRepo;
import com.intership.intershipTHS.repository.StudentRepository;
import com.intership.intershipTHS.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DepartmentRepo departmentRepository;

    @Autowired
    private TeacherRepo teacherRepo;

    @Transactional
    public StudentDto addStudent(StudentDto studentDto) throws Exception {
        System.out.println("Received studentDto: " + studentDto);
        Student student = studentMapper.maptoStudent(studentDto);
        List<String> errorMessages = new ArrayList<>();

        if (studentDto.getDepartment() != null && studentDto.getDepartment().getId() != null) {
            Long deptId = studentDto.getDepartment().getId();
            Optional<Department> deptOpt = departmentRepository.findById(deptId);
            if (deptOpt.isPresent()) {
                student.setDepartment(deptOpt.get());
            } else {
                errorMessages.add("Department not found with id " + deptId);
            }
        }

        if (student.getMobileNumbers() != null) {
            student.getMobileNumbers().forEach(mobileNumber -> mobileNumber.setStudent(student));
        }

        if (student.getTeachers() != null) {
            List<Long> missingTeacherIds = new ArrayList<>();

            Set<Teacher> managedTeachers = student.getTeachers().stream()
                    .map(teacher -> {
                        Optional<Teacher> optionalTeacher = teacherRepo.findById(teacher.getId());
                        if (!optionalTeacher.isPresent()) {
                            missingTeacherIds.add(teacher.getId());
                            return null;
                        }
                        return optionalTeacher.get();
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            if (!missingTeacherIds.isEmpty()) {
                String ids = missingTeacherIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
                errorMessages.add("Teacher not found with id " + ids);
            }

            student.setTeachers(managedTeachers);
        }

        if (!errorMessages.isEmpty()) {
            throw new IllegalArgumentException(String.join(" | ", errorMessages));
        }
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



        if (studentDto.getDepartment() != null && studentDto.getDepartment().getId() != null) {
            Optional<Department> departmentOpt = departmentRepository.findById(studentDto.getDepartment().getId());
            if (departmentOpt.isPresent()) {
                student.setDepartment(departmentOpt.get());
            } else {
                errorMessages.add("Department not found with id " + studentDto.getDepartment().getId());
            }
        }

        updateMobileNumbers(student, studentDto.getMobileNumbers());

        if (studentDto.getTeachers() != null) {
            List<Long> missingTeacherIds = new ArrayList<>();

            Set<Teacher> teachers = studentDto.getTeachers().stream()
                    .map(teacherDto -> {
                        Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherDto.getId());
                        if (!optionalTeacher.isPresent()) {
                            missingTeacherIds.add(teacherDto.getId());
                            return null;
                        }
                        return optionalTeacher.get();
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            if (!missingTeacherIds.isEmpty()) {
                String ids = missingTeacherIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
                errorMessages.add("Teacher not found with id " + ids);
            }

            student.setTeachers(teachers);
        }
        // If any errors collected, throw them all at once
        if (!errorMessages.isEmpty()) {
            throw new IllegalArgumentException(String.join(" | ", errorMessages));
        }


        Student updatedStudent = studentRepository.save(student);
        return studentMapper.maptoStudentDto(updatedStudent);
    }


    private void updateMobileNumbers(Student student, List<MobileNumberDto> updatedNumbers) {
        if (updatedNumbers == null) {
            return;
        }

        List<MobileNumber> existingNumbers = student.getMobileNumbers();


        existingNumbers.removeIf(existing ->
                updatedNumbers.stream().noneMatch(updated ->
                        updated.getId() != null && updated.getId().equals(existing.getId()))
        );


        for (MobileNumberDto updatedNumberDto : updatedNumbers) {
            if (updatedNumberDto.getId() != null) {
                existingNumbers.stream()

                        .filter(existing -> existing.getId().equals(updatedNumberDto.getId()))
                        .findFirst()
                        .ifPresent(existing -> existing.setMobileNumber(updatedNumberDto.getMobileNumber()));
            } else {

                MobileNumber newMobile = new MobileNumber();
                newMobile.setMobileNumber(updatedNumberDto.getMobileNumber());
                newMobile.setStudent(student);
                existingNumbers.add(newMobile);
            }
        }
    }

}
