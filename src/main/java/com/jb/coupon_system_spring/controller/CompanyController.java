package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/Details")
    public ResponseEntity<?> companyDetails() throws CompanyExceptions {
        return new ResponseEntity<>(companyService.companyDetails(), HttpStatus.OK);
    }

    @PostMapping("/addCoupon")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCoupon(@RequestBody Coupon coupon){
        companyService.addCoupon(coupon);
    }

    @PutMapping("/updateCoupon")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestBody Coupon coupon) throws CompanyExceptions {
        companyService.updateCoupon(coupon);
    }

    @DeleteMapping("/deleteCoupon/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCoupon(@PathVariable int id) throws CompanyExceptions {
        companyService.deleteCoupon(id);
    }

    @GetMapping("/allCoupons")
    public ResponseEntity<?> allCoupons() throws CompanyExceptions {
        return new ResponseEntity<>(companyService.allCompanyCoupons(),HttpStatus.OK);
    }

    @GetMapping("/couponsByCategory/{category}")
    public ResponseEntity<?> couponsByCategory(@PathVariable Category category) throws CompanyExceptions {
        return new ResponseEntity<>(companyService.allCompanyCouponsByCategory(category),HttpStatus.OK);
    }

    @GetMapping("/couponsByPrice/{price}")
    public ResponseEntity<?> couponsByPrice(@PathVariable double price) throws CompanyExceptions {
        return new ResponseEntity<>(companyService.allCompanyCouponsByPrice(price),HttpStatus.OK);
    }





}
