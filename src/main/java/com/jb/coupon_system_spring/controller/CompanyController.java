package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.service.CompanyService;
import com.jb.coupon_system_spring.util.JWT;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JWT jwt;

    @GetMapping("/Details")
    public ResponseEntity<?> companyDetails(@RequestHeader(name = "Authorization") String token) throws CompanyExceptions, LoginException {
        String type= jwt.getClientType(token);
        if(type.equals(ClientType.COMPANY.getName())) {
            //return new ResponseEntity<>(companyService.companyDetails(), HttpStatus.OK);
            companyService.setCompanyId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(companyService.companyDetails());
        }else{
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @PostMapping("/addCoupon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addNewCoupon(@RequestHeader(name = "Authorization")String token, @RequestBody Coupon coupon) throws LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.COMPANY.getName())){
            companyService.setCompanyId(jwt.getId(token));
            Map<String,Object> newToken = new HashMap<>();
            newToken.put("Authorization",jwt.checkUser(token));
            companyService.addCoupon(coupon);
            return ResponseEntity.ok(newToken);
        }else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }

    }

    @PutMapping("/updateCoupon")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CompanyExceptions, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.COMPANY.getName())){
            companyService.setCompanyId(jwt.getId(token));
            Map<String,Object> newToken = new HashMap<>();
            newToken.put("Authorization",jwt.checkUser(token));
            companyService.updateCoupon(coupon);
            return ResponseEntity.ok(newToken);
        }else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @DeleteMapping("/deleteCoupon/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "Authorization")String token,@PathVariable int id) throws CompanyExceptions, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.COMPANY.getName())){
            companyService.setCompanyId(jwt.getId(token));
            Map<String,Object> newToken = new HashMap<>();
            newToken.put("Authorization",jwt.checkUser(token));
            companyService.deleteCoupon(id);
            return ResponseEntity.ok(newToken);
        }else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
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
