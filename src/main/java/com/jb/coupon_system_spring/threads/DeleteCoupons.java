package com.jb.coupon_system_spring.threads;

import com.jb.coupon_system_spring.repository.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class DeleteCoupons {
    private final CouponRepo couponRepo;

    @Async
    @Scheduled(cron="0 30 8 * * ?",zone = "Asia/Jerusalem")
    public void deleteCoupons(){
//        couponRepo.deleteCouponPurchase();
        couponRepo.deleteByEndDateBefore(new Date(System.currentTimeMillis()));
    }
}
