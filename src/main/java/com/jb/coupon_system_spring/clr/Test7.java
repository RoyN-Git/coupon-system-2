package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.service.CustomerService;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(7)
@RequiredArgsConstructor
public class Test7 implements CommandLineRunner {
    private final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("TEST 7!!!!!!!!!");
        List<Coupon> couponList=customerService.getCustomerCouponByPrice(50);
        TablePrinter.print(couponList);

        customerService.setCustomerId(2);
        couponList=customerService.getCustomerCoupon();
        TablePrinter.print(couponList);
    }
}
