package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
        private final CompanyRepo companyRepo;
        private final CustomerRepo customerRepo;
        private final CouponRepo couponRepo;
}
