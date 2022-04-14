package com.jb.coupon_system_spring.beans;

import javax.persistence.Entity;

public enum Category {
    ELECTRICITY("Electricity"),
    GAMING("Gaming"),
    MOBILE("Mobile"),
    FOOD("Food"),
    HOME("Home"),
    FASHION("Fashion"),
    COSMETICS("Cosmetics"),
    PHARMACY("Pharmacy"),
    PETS("Pets"),
    TOURISM("Tourism"),
    OUTDOOR("Outdoor"),
    RESTAURANTS("Restaurants");

    private String name;
    Category(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
