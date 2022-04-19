package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;

import java.util.List;

public interface CompanyServiceInterface {
    public void addCoupon(Coupon coupon);
    public void updateCoupon(Coupon coupon) throws CompanyExceptions;
    public void deleteCoupon(int couponId) throws CompanyExceptions;
    public List<Coupon> allCompanyCoupons() throws CompanyExceptions;
    public List<Coupon> allCompanyCouponsByCategory(Category category) throws CompanyExceptions;
    public List<Coupon> allCompanyCouponsByPrice(double price) throws CompanyExceptions;
    public Company companyDetails() throws CompanyExceptions;

}
