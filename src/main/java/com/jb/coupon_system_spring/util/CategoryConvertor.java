package com.jb.coupon_system_spring.util;

import com.jb.coupon_system_spring.beans.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

//@Converter(autoApply = true)
public class CategoryConvertor /*implements AttributeConverter<Category,String>*/ {
/*
    @Override
    public String convertToDatabaseColumn(Category category) {
        if(category==null) {
            return null;
        }
        return category.getName();
    }

    @Override
    public Category convertToEntityAttribute(String name) {
        if(name==null){
            return null;
        }
        return Stream.of(Category.values())
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
        //todo: change it to orElseThrow() when working on exceptions
    }
*/

}
