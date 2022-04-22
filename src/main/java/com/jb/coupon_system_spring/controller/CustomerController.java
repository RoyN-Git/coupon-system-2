package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.exceptions.CouponException;
import com.jb.coupon_system_spring.exceptions.CustomerException;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.service.CustomerService;
import com.jb.coupon_system_spring.util.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JWT jwt;
    @PostMapping("/purchaseCoupon/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCouponPurchase(@PathVariable int couponId) throws  CouponException {
        customerService.purchaseCoupon(couponId);
    }

    @GetMapping("/customerCoupons")
    public ResponseEntity<?> getCustomerCoupons() throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCoupon(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@PathVariable Category category) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCouponByCategory(category),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customerCouponsByPrice/{price}")
    public ResponseEntity<?> getCustomerCouponsByPrice(@PathVariable double price) throws CustomerException {
        return new ResponseEntity<>(customerService.getCustomerCouponByPrice(price), HttpStatus.ACCEPTED);
    }

    @GetMapping("/Details")
    public ResponseEntity<?> customerDetails(@RequestHeader(name = "Authorization") String token) throws CustomerException, LoginException {
        String type= jwt.getClientType(token);
        if(type.equals(ClientType.CUSTOMER.getName())) {
            customerService.setCustomerId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            //return new ResponseEntity<>(customerService.getCustomerDetails(), HttpStatus.OK);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(customerService.getCustomerDetails());
        }else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

}