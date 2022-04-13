package com.jb.coupon_system_spring.repository;

import com.jb.coupon_system_spring.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
}
