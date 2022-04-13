package com.jb.coupon_system_spring.exceptions;

public class CompanyExceptions extends Exception{
    public CompanyExceptions() {
        super("Company exception !");
    }

    public CompanyExceptions(String message) {
        super(message);
    }
}
