package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import com.jb.coupon_system_spring.util.DataEnc;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Creating new companies
//@Component
@Order(1)
@RequiredArgsConstructor
public class Test1 implements CommandLineRunner {
    private final CompanyRepo companyRepo;
    //    private final CouponRepo couponRepo;
    private final int ONE_DAY = 1000 * 60 * 60 * 24;

    @Override
    public void run(String... args) throws Exception {

        for (int counter = 0; counter < 5; counter++) {
            Company company = Company
                    .builder()
                    .email("company" + (counter + 1) + "@company.com")
                    //.password(DataEnc.setEncryptor("company" + (counter + 1)))
                    .password("company" + (counter + 1))
                    .name("company" + (counter + 1))
                    .build();
            companyRepo.save(company);
        }

    }
}
