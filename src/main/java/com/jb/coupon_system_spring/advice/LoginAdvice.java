package com.jb.coupon_system_spring.advice;

import com.jb.coupon_system_spring.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class LoginAdvice {
    @ExceptionHandler(value = {LoginException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail loginError(Exception e){
        return new ErrorDetail("login error",e.getMessage());
    }
}
