package com.jb.coupon_system_spring.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @Singular("coupon")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private List<Coupon> coupons = new ArrayList<>();

//    public void addCoupon(Coupon coupon){
//        this.coupons.add(coupon);
//    }
}


