package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/purchaseCoupon/{couponId}")
    public ResponseEntity<?> addCouponPurchase(@PathVariable double couponId){
        return new ResponseEntity<>(customerService.purchaseCoupon(couponId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customerCoupons")
    public ResponseEntity<?> getCustomerCoupons(){
        return new ResponseEntity<>(customerService.getCustomerCoupon(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@PathVariable Category category){
        return new ResponseEntity<>(customerService.getCustomerCouponByCategory(category),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customerCouponsByPrice/{price}")
    public ResponseEntity<?> getCustomerCouponsByPrice(@PathVariable double price){
        return new ResponseEntity<>(customerService.getCustomerCouponByPrice(price), HttpStatus.ACCEPTED);
    }

}
