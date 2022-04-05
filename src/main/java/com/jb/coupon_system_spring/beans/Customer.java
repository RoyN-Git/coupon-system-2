package com.jb.coupon_system_spring.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @JoinTable(name = "customers_vs_coupons",
               joinColumns =@JoinColumn(name = "customer_id"),
               inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    @Singular
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Coupon> coupons = new ArrayList<>();
}
