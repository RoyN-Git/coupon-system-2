package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepo companyRepo;


}
