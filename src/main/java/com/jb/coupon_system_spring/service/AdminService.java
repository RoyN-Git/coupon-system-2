package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface{
        private final CompanyRepo companyRepo;
        private final CustomerRepo customerRepo;


        @Override
        public void addCompany(Company company) {
                companyRepo.save(company);
        }

        @Override
        public void updateCompany(Company company) {
                if(companyRepo.existsById(company.getId())){
                        companyRepo.save(company);
                }else{
                        System.out.println("no company");
                }
        }

        @Override
        public void deleteCompany(int companyId) {
                if(companyRepo.existsById(companyId)){
                        companyRepo.deleteById(companyId);
                }else{
                        System.out.println("no company");
                }
        }

        @Override
        public List<Company> getAllCompanies() {
                return companyRepo.findAll();
        }

        @Override
        public Company getCompanyById(int companyId) {
                Optional<Company> company=companyRepo.findById(companyId);
                if(company.isPresent()){
                        return company.get();
                }else{
                        System.out.println("no company");
                        return null;
                }
        }

        @Override
        public void addCustomer(Customer customer) {
                customerRepo.save(customer);
        }

        @Override
        public void updateCustomer(Customer customer) {
                if(customerRepo.existsById(customer.getId())){
                        customerRepo.save(customer);
                }else{
                        System.out.println("no customer");
                }
        }

        @Override
        public void deleteCustomer(int customerId) {
                if(customerRepo.existsById(customerId)){
                        customerRepo.deleteById(customerId);
                }else{
                        System.out.println("no customer");
                }
        }

        @Override
        public List<Customer> getAllCustomers() {
              return customerRepo.findAll();
        }

        @Override
        public Customer getCustomerById(int customerId) {
                Optional<Customer> customer =customerRepo.findById(customerId);
                if(customer.isPresent()){
                        return customer.get();
                }else{
                        System.out.println("no customer");
                        return null;
                }
        }
}
