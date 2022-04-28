package com.jb.coupon_system_spring.service;

import com.jb.coupon_system_spring.beans.Category;
import com.jb.coupon_system_spring.beans.Coupon;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.CouponException;
import com.jb.coupon_system_spring.exceptions.CustomerException;
import com.jb.coupon_system_spring.service.interfaces.CustomerServiceInterFace;
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
public class CustomerService extends ClientService implements CustomerServiceInterFace {

    //private int customerId;


    @Override
    public void purchaseCoupon(int couponId) throws CouponException {
        Optional<Coupon> coupon = couponRepo.findById(couponId);
        if (coupon.isPresent()) {
            if (coupon.get().getAmount() == 0) {
                throw new CouponException(ErrorTypes.COUPON_AMOUNT.getMessage());
            }
            if (coupon.get().getEndDate().before(new Date(System.currentTimeMillis()))) {
                throw new CouponException(ErrorTypes.COUPON_EXPIRED.getMessage());
            }
            couponRepo.addCouponPurchase(this.clientId, coupon.get().getId());
            coupon.get().setAmount(coupon.get().getAmount() - 1);
            couponRepo.save(coupon.get());

        } else {
            throw new CouponException(ErrorTypes.COUPON_NOT_EXIST.getMessage());

        }
    }

    @Override
    public List<Coupon> getCustomerCoupon() throws CustomerException {
        if (customerRepo.existsById(this.clientId)) {
            return couponRepo.findCouponsByCustomerId(this.clientId);
        } else {
            throw new CustomerException(ErrorTypes.CUSTOMER_NOT_EXIST.getMessage());
        }
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(Category category) throws CustomerException {
//        return couponRepo.findCouponsByCustomerIdAndCategory(category, customerId);
        //todo: find a way to do it through query, maybe we need to create a class
        return getCustomerCoupon().stream()
                .filter(coupon -> coupon.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Coupon> getCustomerCouponByPrice(double price) throws CustomerException {
        if (customerRepo.existsById(this.clientId)) {
            return couponRepo.findCouponsByCustomerIdUpToPrice(this.clientId, price);
        } else {
            throw new CustomerException(ErrorTypes.CUSTOMER_NOT_EXIST.getMessage());
        }
    }

    @Override
    public Customer getCustomerDetails() throws CustomerException {
        Optional<Customer> customer = customerRepo.findById(this.clientId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerException(ErrorTypes.CUSTOMER_NOT_EXIST.getMessage());
        }
    }
}
