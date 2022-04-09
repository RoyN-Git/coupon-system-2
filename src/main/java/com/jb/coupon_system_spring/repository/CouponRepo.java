package com.jb.coupon_system_spring.repository;

import com.jb.coupon_system_spring.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepo extends JpaRepository<Coupon,Integer> {

}
