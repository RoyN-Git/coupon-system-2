package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceInterface{
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;


    @Override
    public Company companyLogin(String email, String password) throws CompanyExceptions {
        if (companyRepo.existsByEmailAndPassword(email,password)){
            return companyRepo.findByEmailAndPassword(email, password);
        }
        else{
            throw new CompanyExceptions("incorrect email or password !");
        }
    }

    @Override
    public void addCoupon(Coupon coupon) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(Coupon coupon) {

    }

    @Override
    public List<Coupon> allCompanyCoupons() {
        return null;
    }

    @Override
    public List<Coupon> allCompanyCouponsByCategory(Category category) {
        return null;
    }

    @Override
    public List<Coupon> allCompanyCouponsByPrice(int price) {
        return null;
    }

    @Override
    public String companyDetails() {
        return null;
    }
}
