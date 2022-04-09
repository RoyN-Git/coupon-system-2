package com.jb.coupon_system_spring.repository;

import com.jb.coupon_system_spring.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_vs_coupons (customer_id, coupon_id) values (?1, ?2)", nativeQuery = true)
    void addCouponPurchase(int customerId, int couponId);
    //boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
}
