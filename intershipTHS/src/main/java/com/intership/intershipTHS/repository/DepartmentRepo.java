package com.intership.intershipTHS.repository;

import com.intership.intershipTHS.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {
}
