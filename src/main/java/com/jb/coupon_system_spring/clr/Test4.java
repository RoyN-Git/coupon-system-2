package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@RequiredArgsConstructor
@Order(4)
public class Test4 implements CommandLineRunner {
    private final CouponRepo couponRepo;
    private final CustomerRepo customerRepo;
    @Override
    public void run(String... args) throws Exception {
        List<Customer> customers=customerRepo.findAll();
        List<Coupon> coupons=couponRepo.findAll();
        for (Customer customer:customers) {
            for (Coupon coupon:coupons) {
                //couponRepo.addCouponPurchase(customer.getId(),coupon.getId());
            }
        }

    }
}
