package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.service.AdminService;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(8)
@RequiredArgsConstructor
public class Test8 implements CommandLineRunner {
    private final AdminService adminService;
    @Override
    public void run(String... args) throws Exception {
        adminService.deleteCompany(1);
        adminService.deleteCustomer(1);
        TablePrinter.print(adminService.getAllCompanies());
        TablePrinter.print(adminService.getAllCustomers());
    }
}
