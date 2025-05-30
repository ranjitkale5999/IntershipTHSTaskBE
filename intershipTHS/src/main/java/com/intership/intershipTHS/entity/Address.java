package com.intership.intershipTHS.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@ToString(exclude = {"students"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "area")
    private String area;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private Long pincode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference

    private Student student;

}
