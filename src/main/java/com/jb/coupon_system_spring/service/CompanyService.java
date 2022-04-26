package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.ErrorTypes;
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
public class CompanyService implements CompanyServiceInterface {

    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private int companyId;// todo: take care


    @Override
    public void addCoupon(Coupon coupon) {
        couponRepo.save(coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CompanyExceptions {
        if (companyRepo.existsById(coupon.getCompanyId())) {
            if (couponRepo.existsById(coupon.getId())) {
                couponRepo.save(coupon);
            } else {
                throw new CompanyExceptions(ErrorTypes.COUPON_NOT_EXIST.getMessage());
            }
        } else {
            throw new CompanyExceptions(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }

    }

    @Override
    public void deleteCoupon(int couponId) throws CompanyExceptions {
        if (couponRepo.existsById(couponId)) {
            if (couponRepo.getById(couponId).getCompanyId()==this.companyId) {
                couponRepo.deleteCouponPurchase(couponId);
                couponRepo.deleteById(couponId);
            }
        } else {
            throw new CompanyExceptions(ErrorTypes.COUPON_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Coupon> allCompanyCoupons() throws CompanyExceptions {
        if (companyRepo.existsById(companyId)) {
            return couponRepo.findByCompanyId(this.companyId);
        } else {
            throw new CompanyExceptions(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Coupon> allCompanyCouponsByCategory(Category category) throws CompanyExceptions {
        if (companyRepo.existsById(companyId)) {
            return couponRepo.findByCompanyIdAndCategory(this.companyId, category);
        } else {
            throw new CompanyExceptions(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Coupon> allCompanyCouponsByPrice(double price) throws CompanyExceptions {
        if (companyRepo.existsById(companyId)) {
            return couponRepo.findByCompanyIdAndPriceLessThanEqual(this.companyId, price);
        } else {
            throw new CompanyExceptions(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public Company companyDetails() throws CompanyExceptions {
        Optional<Company> company = companyRepo.findById(this.companyId);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new CompanyExceptions(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }
}
