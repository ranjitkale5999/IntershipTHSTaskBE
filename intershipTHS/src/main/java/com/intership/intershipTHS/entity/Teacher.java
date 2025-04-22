package com.intership.intershipTHS.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "teachers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"students"})
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private String subject;

    @ManyToMany(mappedBy = "teachers")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();
}

