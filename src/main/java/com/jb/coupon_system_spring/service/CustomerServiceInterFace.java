package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.CouponException;
import com.jb.coupon_system_spring.exceptions.CustomerException;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterFace {
     void purchaseCoupon(int couponId) throws CouponException;
     List<Coupon> getCustomerCoupon();
     List<Coupon> getCustomerCouponByCategory(Category category);
     List<Coupon> getCustomerCouponByPrice(double price);
     Customer getCustomerDetails() throws CustomerException;



}
