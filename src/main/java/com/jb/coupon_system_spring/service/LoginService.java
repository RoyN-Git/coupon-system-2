package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import com.jb.coupon_system_spring.util.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final JWT jwt;

    public String login(String email, String password, ClientType clientType) throws LoginException {
        switch (clientType) {
            case ADMIN:
                if (email.equals("admin@admin.com") && password.equals("admin")){
                return jwt.generateToken(email);
            }
            case COMPANY:
                Optional<Company> company = companyRepo.findByEmail(email);
                if (company.isPresent()) {
                    if (company.get().getPassword().equals(password)) {
                        return jwt.generateToken(company.get());
                    }
                }
            case CUSTOMER:
                Optional<Customer> customer = customerRepo.findByEmail(email);
                if (customer.isPresent()) {
                    if (customer.get().getPassword().equals(password)) {
                        return jwt.generateToken(customer.get());
                    }
                }
        }
        throw new LoginException("email or password incorrect");

    }

}
