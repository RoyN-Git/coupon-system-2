package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import com.jb.coupon_system_spring.service.CompanyService;
import com.jb.coupon_system_spring.service.CustomerService;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

//@Component
@Order(6)
@RequiredArgsConstructor
public class Test6 implements CommandLineRunner {
    private final int ONE_DAY=1000*60*60*24;
    private final CompanyService companyService;
    //private final CustomerService customerService;
    @Override
    public void run(String... args) throws Exception {
        List<Coupon> couponList;

        companyService.addCoupon(Coupon.builder()
                .companyId(companyService.getId())
                .category(Category.HOME)
                .amount(100)
                .description("check")
                .title("check")
                .price(Math.random()*100+1)
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()+(int)(Math.random()*7+1)*ONE_DAY))
                .image("image")
                .build());
        couponList=companyService.allCompanyCouponsByCategory(Category.HOME);
        System.out.println("company coupons by category");
        TablePrinter.print(couponList);

        //customerService.purchaseCoupon();
    }
}
