package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CompanyService implements CompanyServiceInterface{

    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private int companyId=1;// todo: take care


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
    public void updateCoupon(Coupon coupon) throws CompanyExceptions {  // todo: add if for checking if the coupon is exist
        if(companyRepo.existsById(coupon.getCompanyId())){
            if (couponRepo.existsById(coupon.getId())) {
                couponRepo.save(coupon);
            }
            else {
                throw new CompanyExceptions("coupon not exists");
            }
        }else{
            throw new CompanyExceptions("company no exists");
        }

    }

    @Override
    public void deleteCoupon(int couponId) {
        if(couponRepo.existsById(couponId)) {
            couponRepo.deleteById(couponId);
        }else{
            System.out.println("no coupon");
        }
    }

    @Override
    public List<Coupon> allCompanyCoupons() {
        return couponRepo.findByCompanyId(this.companyId);
    }

    @Override
    public List<Coupon> allCompanyCouponsByCategory(Category category) {
        return couponRepo.findByCompanyIdAndCategory(this.companyId,category);
    }

    @Override
    public List<Coupon> allCompanyCouponsByPrice(double price) {
        return couponRepo.findByCompanyIdAndPriceLessThanEqual(this.companyId,price);
    }

    @Override
    public Company companyDetails() throws CompanyExceptions {
        Optional<Company> company=companyRepo.findById(this.companyId);
        if(company.isPresent()){
            return company.get();
        }else{
            throw new CompanyExceptions("no company");
        }
    }
}
