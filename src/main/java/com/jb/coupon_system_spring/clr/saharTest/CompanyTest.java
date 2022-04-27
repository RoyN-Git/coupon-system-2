package com.jb.coupon_system_spring.clr.saharTest;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.exceptions.CompanyException;
import com.jb.coupon_system_spring.service.AdminService;
import com.jb.coupon_system_spring.service.CompanyService;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//@Component
@Order(2)
@RequiredArgsConstructor
public class CompanyTest implements CommandLineRunner {

    private final CompanyService companyService;
    private final AdminService adminService;
    private final int ONE_DAY=1000*60*60*24;

    @Override
    public void run(String... args) throws Exception {
        Company company = adminService.getCompanyById(1);
        companyService.setId(company.getId());
        addCoupons(company);
        updateCoupon();
        deleteCoupon();
        printCoupons();
        companyDetails();
//        exceptions();
    }

    public void addCoupons(Company company){
        List<Coupon> coupons=new ArrayList<>();
        for (int counter = 0; counter < 3; counter++) {
            Coupon coupon= Coupon
                    .builder()
                    .companyId(company.getId())
                    .category(Category.ELECTRICITY)
                    .amount(100)
                    .description("coupon number "+(counter+1))
                    .title("coupon title "+(counter+1))
                    .price(Math.random()*100+1)
                    .startDate(new Date(System.currentTimeMillis()))
                    .endDate(new Date(System.currentTimeMillis()+(int)(Math.random()*7+1)*ONE_DAY))
                    .image("image")
                    .build();
            companyService.addCoupon(coupon);
            //companies.get(counter).addCoupon(coupon);
        }

    }

    public void updateCoupon() throws CompanyException {
        Coupon coupon =companyService.allCompanyCoupons().get(0);
        coupon.setPrice(33);
        companyService.updateCoupon(coupon);
    }

    public void deleteCoupon() throws CompanyException {
        companyService.deleteCoupon(2);
    }

    public void printCoupons(){
//        System.out.println("all coupons :");
//        TablePrinter.print(companyService.allCompanyCoupons());
//        System.out.println("all coupons by category");
//        TablePrinter.print(companyService.allCompanyCouponsByCategory(Category.ELECTRICITY));
//        System.out.println("all coupons by price ");
//        TablePrinter.print(companyService.allCompanyCouponsByPrice(50));

    }

    public void companyDetails(){
//        try {
//            TablePrinter.print(companyService.companyDetails());
//        } catch (CompanyExceptions e) {
//            throw new RuntimeException(e);
//        }
    }

    public void exceptions() throws CompanyException {
//        companyService.deleteCoupon(9);  // todo: take care of the exception
        TablePrinter.print(companyService.companyDetails()); // problem with the company id


        Coupon coupon= Coupon
                .builder()
//                .companyId(5)  // todo: take care of the exception
                .category(Category.ELECTRICITY)
                .amount(100)
                .description("coupon number "+(5))
                .title("coupon title "+(7))
                .price(Math.random()*100+1)
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()+(int)(Math.random()*7+1)*ONE_DAY))
                .image("image")
                .build();
        companyService.updateCoupon(coupon);
    }


}
