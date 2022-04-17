package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/Details")
    public ResponseEntity<?> companyDetails() throws CompanyExceptions {
        return new ResponseEntity<>(companyService.companyDetails(), HttpStatus.OK);
    }
}
