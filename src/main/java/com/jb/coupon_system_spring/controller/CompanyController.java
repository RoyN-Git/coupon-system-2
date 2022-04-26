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
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<?> companyDetails
            (@RequestHeader(name = "Authorization") String token)
            throws CompanyExceptions, LoginException {
        String type= jwt.getClientType(token);
        if(type.equals(ClientType.COMPANY.getName())) {
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
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addNewCoupon
            (@RequestHeader(name = "Authorization")String token, @RequestBody Coupon coupon)
            throws LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.COMPANY.getName())){
            companyService.setCompanyId(jwt.getId(token));
            String newToken= jwt.checkUser(token);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", newToken);
            companyService.addCoupon(coupon);
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }

    }

    @PutMapping("/updateCoupon")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCoupon
            (@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon)
            throws CompanyExceptions, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.COMPANY.getName())){
            companyService.setCompanyId(jwt.getId(token));
            String newToken= jwt.checkUser(token);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", newToken);
            companyService.updateCoupon(coupon);
            return new ResponseEntity<>(headers,HttpStatus.ACCEPTED);
        }else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @DeleteMapping("/deleteCoupon/{id}")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCoupon
            (@RequestHeader(name = "Authorization")String token,@PathVariable int id)
            throws CompanyExceptions, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.COMPANY.getName())){
            companyService.setCompanyId(jwt.getId(token));
            String newToken= jwt.checkUser(token);
            HttpHeaders headers=new HttpHeaders();
            headers.add("Authorization",newToken);
            companyService.deleteCoupon(id);
            return new ResponseEntity<>(headers,HttpStatus.ACCEPTED);
        }else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @GetMapping("/allCoupons")
    public ResponseEntity<?> allCoupons
            (@RequestHeader(name = "Authorization")String token)
            throws CompanyExceptions, LoginException {
        String type= jwt.getClientType(token);
        if(type.equals(ClientType.COMPANY.getName())) {
            companyService.setCompanyId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(companyService.allCompanyCoupons());
        }else{
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @GetMapping("/couponsByCategory/{category}")
    public ResponseEntity<?> couponsByCategory
            (@RequestHeader(name = "Authorization")String token,@PathVariable Category category)
            throws CompanyExceptions, LoginException {
        String type= jwt.getClientType(token);
        if(type.equals(ClientType.COMPANY.getName())) {
            companyService.setCompanyId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(companyService.allCompanyCouponsByCategory(category));
        }else{
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @GetMapping("/couponsByPrice/{price}")
    public ResponseEntity<?> couponsByPrice(
            @RequestHeader(name = "Authorization")String token,@PathVariable double price)
            throws CompanyExceptions, LoginException {
        String type= jwt.getClientType(token);
        if(type.equals(ClientType.COMPANY.getName())) {
            companyService.setCompanyId(jwt.getId(token));
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(companyService.allCompanyCouponsByPrice(price));
        }else{
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

}
