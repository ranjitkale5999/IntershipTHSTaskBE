package com.intership.intershipTHS.controller;

import com.intership.intershipTHS.dto.FieldErrorDTO;
import com.intership.intershipTHS.dto.TeacherDto;
import com.intership.intershipTHS.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v3")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @PostMapping("/teacher")
    public ResponseEntity<?> addTeacher(
            @Valid @RequestBody TeacherDto teacherDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<FieldErrorDTO> errors = bindingResult.getFieldErrors().stream()
                    .map(fe -> new FieldErrorDTO(fe.getField(), fe.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }

        TeacherDto saved = teacherService.addTeacher(teacherDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping(value = "/teacher")
    public List<TeacherDto> getAllTeacher(){
        return teacherService.getAllTeacher();
    }


}
