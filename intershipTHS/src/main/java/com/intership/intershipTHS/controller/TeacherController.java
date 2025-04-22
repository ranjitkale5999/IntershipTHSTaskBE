package com.intership.intershipTHS.controller;

import com.intership.intershipTHS.dto.TeacherDto;
import com.intership.intershipTHS.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v3")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @PostMapping("/teacher")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacherDto){
        TeacherDto saveTeacher=teacherService.addTeacher(teacherDto);
        return new ResponseEntity<>(saveTeacher, HttpStatus.CREATED);
    }

    @GetMapping(value = "/teacher")
    public List<TeacherDto> getAllTeacher(){
        return teacherService.getAllTeacher();
    }


}
