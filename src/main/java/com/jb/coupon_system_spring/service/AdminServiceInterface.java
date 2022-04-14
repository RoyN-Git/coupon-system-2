package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.CompanyExceptions;

import java.util.List;

public interface AdminServiceInterface {
    void addCompany(Company company);
    void updateCompany(Company company) throws CompanyExceptions;
    void deleteCompany(int companyId) throws CompanyExceptions;
    List<Company> getAllCompanies();
    Company getCompanyById(int companyId);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
}
