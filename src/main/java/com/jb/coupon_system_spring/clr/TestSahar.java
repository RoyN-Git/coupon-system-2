package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class TestSahar implements CommandLineRunner {
    private final CouponRepo couponRepo;
    private final CompanyRepo companyRepo;

    @Override
    public void run(String... args) throws Exception {
//        List<Company> companies = companyRepo.findByEmailAndPassword("company1@company.com","company1");
//        System.out.println(companyRepo.findByEmail("company1@company.com").isPresent());
        System.out.println(companyRepo.existsByEmailAndPassword("company1@company.com","company1"));


    }
}
