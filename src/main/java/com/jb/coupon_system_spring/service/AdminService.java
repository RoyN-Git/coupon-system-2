package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.AdminException;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import com.jb.coupon_system_spring.util.DataEnc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;


    @Override
    public void addCompany(Company company) {
        company.setPassword(DataEnc.setEncryptor(company.getPassword()));
        companyRepo.save(company);
    }

    @Override
    public void updateCompany(Company company) throws AdminException {
        if (companyRepo.existsById(company.getId())) {
            if(!companyRepo.findById(company.getId()).get().getName().equals(company.getName())){
                throw new AdminException(ErrorTypes.UNCHANGED_VALUE.getMessage());
            }
            company.setPassword(DataEnc.setEncryptor(company.getPassword()));
            companyRepo.save(company);
        } else {
            throw new AdminException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public void deleteCompany(int companyId) throws AdminException {
        if (companyRepo.existsById(companyId)) {
            for (Coupon coupon : getCompanyById(companyId).getCoupons()) {
                couponRepo.deleteCouponPurchase(coupon.getId());
                couponRepo.deleteById(coupon.getId());
            }
            companyRepo.deleteById(companyId);
        } else {
            throw new AdminException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyById(int companyId) throws AdminException {
        Optional<Company> company = companyRepo.findById(companyId);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new AdminException(ErrorTypes.COMPANY_NOT_EXIST.getMessage());
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        customer.setPassword(DataEnc.setEncryptor(customer.getPassword()));
        customerRepo.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws AdminException {
        if (customerRepo.existsById(customer.getId())) {
            customer.setPassword(DataEnc.setEncryptor(customer.getPassword()));
            customerRepo.save(customer);
        } else {
            throw new AdminException(ErrorTypes.CUSTOMER_NOT_EXIST.getMessage());
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws AdminException {
        if (customerRepo.existsById(customerId)) {
            couponRepo.deleteCouponPurchaseByCustomerId(customerId);
            customerRepo.deleteById(customerId);
        } else {
            throw new AdminException(ErrorTypes.CUSTOMER_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(int customerId) throws AdminException {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new AdminException(ErrorTypes.CUSTOMER_NOT_EXIST.getMessage());
        }
    }
}
