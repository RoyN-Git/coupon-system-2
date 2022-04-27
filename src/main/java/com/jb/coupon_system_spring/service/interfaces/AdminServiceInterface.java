package com.jb.coupon_system_spring.service.interfaces;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.AdminException;

import java.util.List;

public interface AdminServiceInterface {
    void addCompany(Company company);
    void updateCompany(Company company) throws AdminException;
    void deleteCompany(int companyId) throws AdminException;
    List<Company> getAllCompanies();
    Company getCompanyById(int companyId) throws AdminException;
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer) throws AdminException;
    void deleteCustomer(int customerId) throws AdminException;
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId) throws AdminException;
}
