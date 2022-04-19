package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.CouponException;
import com.jb.coupon_system_spring.exceptions.CustomerException;
import com.jb.coupon_system_spring.repository.CompanyRepo;
import com.jb.coupon_system_spring.repository.CouponRepo;
import com.jb.coupon_system_spring.repository.CustomerRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CustomerService implements CustomerServiceInterFace {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;

    private int customerId = 0; // todo: take care
    private List<Coupon> couponsToPurchase;

    @Override
    public void purchaseCoupon(int couponId) throws CouponException {
        Optional<Coupon> coupon=couponRepo.findById(couponId);
        if(coupon.isPresent()) {
            if (coupon.get().getAmount() == 0) {
                throw new CouponException("coupon amount is 0");
            }
            if (coupon.get().getEndDate().before(new Date(System.currentTimeMillis()))) {
                throw new CouponException("expired coupon");
            }
            couponRepo.addCouponPurchase(customerId, coupon.get().getId());
            coupon.get().setAmount(coupon.get().getAmount() - 1);
            couponRepo.save(coupon.get());
        }else {
            throw new CouponException("no coupon");

        }
    }

    @Override
    public List<Coupon> getCustomerCoupon() {
        return couponRepo.findCouponsByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(Category category) {
        //return couponRepo.findCouponsByCustomerIdAndCategory(customerId,category);
        return getCustomerCoupon().stream()
                .filter(coupon -> coupon.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Coupon> getCustomerCouponByPrice(double price) {
        return couponRepo.findCouponsByCustomerIdUpToPrice(customerId,price);
    }

    @Override
    public Customer getCustomerDetails() throws CustomerException {
        Optional<Customer> customer = customerRepo.findById(this.customerId);
        if (customer.isPresent()){
            return customer.get();
        }
        else {
            throw new CustomerException("no customer");
        }
    }
}
