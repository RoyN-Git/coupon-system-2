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
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceInterface{
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private int companyId=1;//for testing


    @Override
    public Company companyLogin(String email, String password) throws CompanyExceptions {
//        if (companyRepo.existsByEmailAndPassword(email,password)){
//            return companyRepo.findByEmailAndPassword(email, password);
//        }
//        else{
//            throw new CompanyExceptions("incorrect email or password !");
//        }
        /*
        Optional<Company> company=companyRepo.findByEmailAndPassword(email, password);
        if(company.isPresent()){
            return company.get();
        }else{
            throw new CompanyExceptions("c");
        }

         */
        return null;
    }

    @Override
    public void addCoupon(Coupon coupon) {
        couponRepo.save(coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CompanyExceptions {
        if(companyRepo.existsById(coupon.getCompanyId())){
            couponRepo.save(coupon);
        }else{
            throw new CompanyExceptions("company no exists");
        }

    }

    @Override
    public void deleteCoupon(int couponId) {
        if(couponRepo.existsById(couponId)) {
            couponRepo.deleteById(couponId);
        }else{
            //todo:add coupon exception
        }
    }

    @Override
    public List<Coupon> allCompanyCoupons() {
        return couponRepo.findByCompanyId(this.companyId);
    }

    @Override
    public List<Coupon> allCompanyCouponsByCategory(Category category) {
        return couponRepo.findByCompanyIdAndCategory(this.companyId,category.ordinal()+1);
    }

    @Override
    public List<Coupon> allCompanyCouponsByPrice(double price) {
        return couponRepo.findByCompanyIdAndPriceLessThanEqual(this.companyId,price);
    }

    @Override
    public Company companyDetails(int id) {
        Optional<Company> company=companyRepo.findById(id);
        //todo: add exception
        return company.orElse(null);
    }
}
