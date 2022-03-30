package com.jb.coupon_system_spring.repository;

import com.jb.coupon_system_spring.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
