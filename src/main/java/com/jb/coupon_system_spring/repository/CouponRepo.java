package com.jb.coupon_system_spring.repository;

import com.jb.coupon_system_spring.beans.Category;
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

    @Query(value = "SELECT coupons.*" +
            " FROM coupons, customers_vs_coupons" +
            " WHERE customers_vs_coupons.customer_id=? " +
            " AND coupons.id=customers_vs_coupons.coupon_id",
            nativeQuery = true)
    List<Coupon> findCouponsByCustomerId(int customerId);
    @Query(value = "SELECT coupons.*" +
            " FROM coupons, customers_vs_coupons" +
            " WHERE customers_vs_coupons.customer_id=? " +
            " AND coupons.category_id=?" +
            " AND coupons.id=customers_vs_coupons.coupon_id",
            nativeQuery = true)
    List<Coupon> findCouponsByCustomerIdAndCategory(int customerId, Category category);
    @Query(value = "SELECT coupons.*" +
            " FROM coupons, customers_vs_coupons" +
            " WHERE customers_vs_coupons.customer_id=? " +
            " AND coupons.price<=?" +
            " AND coupons.id=customers_vs_coupons.coupon_id",
            nativeQuery = true)
    List<Coupon> findCouponsByCustomerIdUpToPrice(int customerId,double price);



    @Transactional
    @Modifying
    @Query(value = "DELETE from customers_vs_coupons where coupons_id = ?1", nativeQuery = true)
    void deleteCouponPurchase(int couponId);

    @Transactional
    @Modifying
    @Query(value = "DELETE from customers_vs_coupons where customer_id = ?1", nativeQuery = true)
    void deleteCouponPurchaseByCustomerId(int customerId);



}
