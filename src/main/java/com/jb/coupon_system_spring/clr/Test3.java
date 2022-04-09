package com.jb.coupon_system_spring.clr;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//Creating new coupon for each company
@Component
@RequiredArgsConstructor
@Order(3)
public class Test3 implements CommandLineRunner {
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private final int ONE_DAY=1000*60*60*24;
    @Override
    public void run(String... args) throws Exception {

        System.out.println("test 3");

        List<Company> companies=companyRepo.findAll();
        List<Coupon> coupons=new ArrayList<>();
        for (int counter = 0; counter < companies.size(); counter++) {
            Coupon coupon= Coupon
                    .builder()
                    .companyId(companies.get(counter).getId())
                    .category(Category.ELECTRICITY)
                    .amount(100)
                    .description("coupon number "+(counter+1))
                    .title("coupon title "+(counter+1))
                    .price(Math.random()*100+1)
                    .startDate(new Date(System.currentTimeMillis()))
                    .endDate(new Date(System.currentTimeMillis()+(int)(Math.random()*7+1)*ONE_DAY))
                    .image("image")
                    .build();
            coupons.add(coupon);
            //companies.get(counter).addCoupon(coupon);
        }
        //companyRepo.saveAll(companies);
        couponRepo.saveAll(coupons);
        TablePrinter.print(couponRepo.findAll());
        //companyRepo.deleteById(1);

    }
}
