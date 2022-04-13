package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterFace {
     void purchaseCoupon(Coupon coupon);
     List<Coupon> getCustomerCoupon();
     List<Coupon> getCustomerCouponByCategory(Category category);
     List<Coupon> getCustomerCouponByPrice(double price);
     Customer getCustomerDetails(int id);



}
