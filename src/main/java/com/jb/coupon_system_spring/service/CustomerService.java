package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
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
public class CustomerService implements CustomerServiceInterFace {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;

    private int customerId = 1; // for testing

    @Override
    public void purchaseCoupon(Coupon coupon) {

    }

    @Override
    public List<Coupon> getCustomerCoupon() {

        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(Category category) {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponByPrice(double price) {
        return null;
    }

    @Override
    public Customer getCustomerDetails(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()){
            return customer.get();
        }
        else {
            System.out.println("no customer!");
            return null;
        }
    }
}
