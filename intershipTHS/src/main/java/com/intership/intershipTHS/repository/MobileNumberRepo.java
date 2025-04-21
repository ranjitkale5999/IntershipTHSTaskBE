package com.intership.intershipTHS.repository;

import com.intership.intershipTHS.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileNumberRepo extends JpaRepository<MobileNumber,Long> {
}
