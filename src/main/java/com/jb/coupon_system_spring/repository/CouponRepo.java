package com.jb.coupon_system_spring.repository;

import com.jb.coupon_system_spring.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CouponRepo extends JpaRepository<Coupon,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_vs_coupons (customer_id, coupon_id) values (?1, ?2)", nativeQuery = true)
    void addCouponPurchase(int customerId, int couponId);

    List<Coupon> findByCompanyIdAndCategory(int companyId,int categoryId);
    List<Coupon> findByCompanyId(int companyId);
    List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId,double price);
    List<Coupon> findByIdAndCustomerId(int couponId,int customerId);

}
