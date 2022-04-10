package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepo companyRepo;

    public Company companyLogin(String email, String password) throws CompanyExceptions {
        if (companyRepo.existsByEmailAndPassword(email,password)){
            return companyRepo.findByEmailAndPassword(email, password);
        }
        else{
            throw new CompanyExceptions("incorrect email or password !");
        }

    }


}
