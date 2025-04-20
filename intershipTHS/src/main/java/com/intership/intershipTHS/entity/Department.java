package com.intership.intershipTHS.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name ="department")
@ToString(exclude = {"students"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;


}
