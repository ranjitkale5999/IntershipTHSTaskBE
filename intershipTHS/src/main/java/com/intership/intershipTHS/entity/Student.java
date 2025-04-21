package com.intership.intershipTHS.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name ="student")
//@ToString(exclude = {"mobileNumbers","teachers","addresses", "department"})
@ToString(exclude = {"department","mobileNumbers"})
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

        @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<MobileNumber> mobileNumbers;

}
