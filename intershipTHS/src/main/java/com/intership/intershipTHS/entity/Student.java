package com.intership.intershipTHS.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="student")
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name="student_name")
        private String studentName;
        private int age;
}
