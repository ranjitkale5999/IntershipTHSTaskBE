package com.intership.intershipTHS.controller;

import com.intership.intershipTHS.Exceptions.ErrorResponse;
import com.intership.intershipTHS.dto.FieldErrorDTO;
import com.intership.intershipTHS.dto.TeacherDto;
import com.intership.intershipTHS.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v3")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @PostMapping("/teacher")
    public ResponseEntity<ErrorResponse<TeacherDto>> addTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        TeacherDto savedTeacher = teacherService.addTeacher(teacherDto);

        ErrorResponse<TeacherDto> response = new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Teacher created successfully",
                savedTeacher
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(value = "/teacher/{id}")
    public ResponseEntity<ErrorResponse<?>> getByIdTeacher(@PathVariable Long id) {
        TeacherDto teachers = teacherService.getByIdTeachers(id);

        ErrorResponse<TeacherDto> response = new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Teacher data fetched by Id :-" + id,
                teachers
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/teacher")
    public ResponseEntity<ErrorResponse<List<TeacherDto>>> getAllTeacher() {
        List<TeacherDto> teachers = teacherService.getAllTeacher();

        ErrorResponse<List<TeacherDto>> response = new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Teacher list fetched successfully",
                teachers
        );

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<ErrorResponse<Object>> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);

        ErrorResponse<Object> response = new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Teacher deleted successfully with ID: " + id,
                null
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity<ErrorResponse<TeacherDto>> updateTeacher(
            @Valid @PathVariable Long id,
            @Valid @RequestBody TeacherDto teacherDto
    ) {
        TeacherDto updatedTeacher = teacherService.updateTeacher(id, teacherDto);

        ErrorResponse<TeacherDto> response = new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Teacher updated successfully with ID: " + id,
                updatedTeacher
        );

        return ResponseEntity.ok(response);
    }


}
