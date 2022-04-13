package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import com.jb.coupon_system_spring.service.AdminService;
import com.jb.coupon_system_spring.service.CompanyService;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//Testing admin service
@Component
@Order(5)
@RequiredArgsConstructor
public class Test5 implements CommandLineRunner {
    private final AdminService adminService;


    @Override
    public void run(String... args) throws Exception {
        adminService.addCompany(Company.builder()
                .email("newComapny@comapny.com")
                .password("123456")
                .name("my company")
                .build());
        List<Company> companiesFromAdmin = adminService.getAllCompanies();
        TablePrinter.print(companiesFromAdmin);
        Company singleCompany = adminService.getCompanyById(6);
        TablePrinter.print(singleCompany);
        singleCompany.setEmail("change@company.com");
        adminService.updateCompany(singleCompany);
        //adminService.deleteCompany(6);

        adminService.addCustomer(Customer.builder()
                .email("newCustomer@customer.com")
                .password("213456")
                .firstName("my")
                .lastName("customer")
                .build());
        List<Customer> customersFromAdmin=adminService.getAllCustomers();
        TablePrinter.print(customersFromAdmin);
        Customer singleCustomer=adminService.getCustomerById(6);
        TablePrinter.print(singleCustomer);
        singleCustomer.setFirstName("change");
        adminService.updateCustomer(singleCustomer);
        //adminService.deleteCustomer(6);

    }
}
