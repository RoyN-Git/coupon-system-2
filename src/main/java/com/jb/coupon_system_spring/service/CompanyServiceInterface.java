package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;

import java.util.List;

public interface CompanyServiceInterface {
    public Company companyLogin(String email,String password) throws CompanyExceptions;
    public void addCoupon(Coupon coupon);
    public void updateCoupon(Coupon coupon);
    public void deleteCoupon(Coupon coupon);
    public List<Coupon> allCompanyCoupons();
    public List<Coupon> allCompanyCouponsByCategory(Category category);
    public List<Coupon> allCompanyCouponsByPrice(int price);
    public String companyDetails();

}
