package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//Testing deleting entities
@Component
@Order(5)
@RequiredArgsConstructor
public class Test5 implements CommandLineRunner {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;
    @Override
    public void run(String... args) throws Exception {
        //companyRepo.deleteById(1);

    }
}
