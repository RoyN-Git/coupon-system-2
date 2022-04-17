package com.jb.coupon_system_spring.clr.saharTest;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.AdminException;
import com.jb.coupon_system_spring.service.AdminService;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(1)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {

    private final AdminService  adminService;

    @Override
    public void run(String... args) throws Exception {

        add();
        getAndUpdate();
        delete();
        showCompanies();
        addCustomer();
        getAndUpdateCustomer();
        deleteCustomer();
        showAllCustomer();
        exceptionsTests();


    }

    public void add(){
        Company company = Company
                .builder()
                .email("company" + (1) + "@company.com")
                .password("company" + (1))
                .name("company" + (1))
                .build();

        Company company2 = Company
                .builder()
                .email("company" + (2) + "@company.com")
                .password("company" + (2))
                .name("company" + (2))
                .build();

        Company company3 = Company
                .builder()
                .email("company" + (3) + "@company.com")
                .password("company" + (3))
                .name("company" + (3))
                .build();

        adminService.addCompany(company);
        adminService.addCompany(company2);
        adminService.addCompany(company3);
    }

    public void getAndUpdate(){
//        Company company = adminService.getCompanyById(1);
//        company.setPassword("updatepass");
//        adminService.updateCompany(company);
    }

    public void delete(){
//        adminService.deleteCompany(2);
    }

    public void showCompanies(){
        TablePrinter.print(adminService.getAllCompanies());
    }

    public void addCustomer(){
        Customer customer= Customer
                .builder()
                .email("customer"+(1)+"@customer.com")
                .password("customer"+(1))
                .firstName("customer first"+(1))
                .lastName("customer last"+(1))
                .build();

        Customer customer2= Customer
                .builder()
                .email("customer"+(2)+"@customer.com")
                .password("customer"+(2))
                .firstName("customer first"+(2))
                .lastName("customer last"+(2))
                .build();

        Customer customer3= Customer
                .builder()
                .email("customer"+(3)+"@customer.com")
                .password("customer"+(3))
                .firstName("customer first"+(3))
                .lastName("customer last"+(3))
                .build();
        adminService.addCustomer(customer);
        adminService.addCustomer(customer2);
        adminService.addCustomer(customer3);
    }

    public void getAndUpdateCustomer(){
//        Customer customer = adminService.getCustomerById(3);
//        customer.setPassword("update");
//        adminService.updateCustomer(customer);
    }

    public void deleteCustomer() throws AdminException {
        adminService.deleteCustomer(1);
    }

    public void showAllCustomer(){
        TablePrinter.print(adminService.getAllCustomers());
    }

    public void exceptionsTests(){

//        Company company = Company
//                .builder()
//                .email("company" + (1) + "@company.com")
//                .password("company" + (1))
//                .name("company" + (1))
//                .build();
//
//        adminService.updateCompany(company);
//        Company company1 = adminService.getCompanyById(9);
//        adminService.deleteCompany(5);

//        Customer customer= Customer
//                .builder()
//                .email("customer"+(1)+"@customer.com")
//                .password("customer"+(1))
//                .firstName("customer first"+(1))
//                .lastName("customer last"+(1))
//                .build();
//        adminService.updateCustomer(customer);
//        Customer customer1 = adminService.getCustomerById(22);
//        adminService.deleteCustomer(7);

    }



}
