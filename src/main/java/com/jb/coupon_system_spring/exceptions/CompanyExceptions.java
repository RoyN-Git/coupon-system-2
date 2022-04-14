package com.jb.coupon_system_spring.exceptions;

import com.jb.coupon_system_spring.beans.Company;

import java.util.function.Supplier;

public class CompanyExceptions extends Exception {
    public CompanyExceptions() {
        super("Company exception !");
    }

    public CompanyExceptions(String message) {
        super(message);
    }
}
