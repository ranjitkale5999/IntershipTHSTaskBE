package com.intership.intershipTHS.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name ="student")
@ToString(exclude = {"department","mobileNumbers","teachers","addresses"})
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

        @ManyToMany
        @JoinTable(
                name = "student_teacher",
                joinColumns = @JoinColumn(name = "student_id"),
                inverseJoinColumns = @JoinColumn(name = "teacher_id")
        )
        @JsonManagedReference
        private Set<Teacher> teachers = new HashSet<>();

        @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<Address> addresses;
}
