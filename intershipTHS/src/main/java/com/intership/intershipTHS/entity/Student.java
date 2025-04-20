package com.intership.intershipTHS.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name ="student")
//@ToString(exclude = {"mobileNumbers","teachers","addresses", "department"})
@ToString(exclude = {"department"})
public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name="student_name")
        private String studentName;
        private int age;
        @ManyToOne
        @JoinColumn(name = "department_id")
        private Department department;


}
