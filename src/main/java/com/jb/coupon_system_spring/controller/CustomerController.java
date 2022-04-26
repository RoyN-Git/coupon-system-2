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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JWT jwt;

    @PostMapping("/purchaseCoupon/{couponId}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCouponPurchase
            (@RequestHeader(name = "Authorization") String token, @PathVariable int couponId)
            throws CouponException, LoginException {
        String type=jwt.getClientType(token);
        if(type.equals(ClientType.CUSTOMER.getName())){
            customerService.setCustomerId(jwt.getId(token));
            String newToken= jwt.checkUser(token);
            Map<String,String> headers=new HashMap<>();
            headers.put("Authorization",newToken);
            customerService.purchaseCoupon(couponId);
//            return ResponseEntity.ok()
//                    .header("Authorization",newToken)
//                    .body();
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }else{
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
        //customerService.purchaseCoupon(couponId);
    }

    @GetMapping("/customerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws CustomerException, LoginException {
        //return new ResponseEntity<>(customerService.getCustomerCoupon(),HttpStatus.ACCEPTED);
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.CUSTOMER.getName())) {
            customerService.setCustomerId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(customerService.getCustomerCoupon());
        } else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @GetMapping("/customerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory
            (@RequestHeader(name = "Authorization") String token,@PathVariable Category category)
            throws CustomerException, LoginException {
        //return new ResponseEntity<>(customerService.getCustomerCouponByCategory(category), HttpStatus.ACCEPTED);
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.CUSTOMER.getName())) {
            customerService.setCustomerId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(customerService.getCustomerCouponByCategory(category));
        } else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @GetMapping("/customerCouponsByPrice/{price}")
    public ResponseEntity<?> getCustomerCouponsByPrice(
            @RequestHeader(name = "Authorization") String token,@PathVariable double price)
            throws CustomerException, LoginException {
        //return new ResponseEntity<>(customerService.getCustomerCouponByPrice(price), HttpStatus.ACCEPTED);
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.CUSTOMER.getName())) {
            customerService.setCustomerId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(customerService.getCustomerCouponByPrice(price));
        } else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @GetMapping("/Details")
    public ResponseEntity<?> customerDetails(@RequestHeader(name = "Authorization") String token) throws CustomerException, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.CUSTOMER.getName())) {
            customerService.setCustomerId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(customerService.getCustomerDetails());
        } else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

}