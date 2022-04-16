package com.jb.coupon_system_spring.advice;

import com.jb.coupon_system_spring.exceptions.AdminException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class AdminAdvice {
    @ExceptionHandler(value = {AdminException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleAdminException(Exception e){
        return new ErrorDetail("admin error",e.getMessage());
    }
}
