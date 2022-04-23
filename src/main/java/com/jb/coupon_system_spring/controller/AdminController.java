package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.beans.ErrorTypes;
import com.jb.coupon_system_spring.exceptions.AdminException;
import com.jb.coupon_system_spring.exceptions.LoginException;
import com.jb.coupon_system_spring.service.AdminService;
import com.jb.coupon_system_spring.util.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final JWT jwt;

    private final AdminService adminService;

    @GetMapping("/allCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws LoginException {
        String type=jwt.getClientType(token);
        if(type.equals(ClientType.ADMIN.getName())) {
            String newToken = jwt.checkUser(token);
            //return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(adminService.getAllCompanies());
        }else{
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }
    @GetMapping("/allCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws LoginException{
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())){
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok().header("Authorization",newToken)
                    .body(adminService.getAllCustomers());
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
//        return new ResponseEntity<>(adminService.getAllCustomers(),HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getCompanyById(@RequestHeader(name = "Authorization") String token,@PathVariable int id) throws AdminException, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())){
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok().
                    header("Authorization",newToken)
                    .body(adminService.getCompanyById(id));
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
//        return new ResponseEntity<>(adminService.getCompanyById(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@RequestHeader(name = "Authorization")String token,@PathVariable int id) throws AdminException, LoginException {
//        eturn new ResponseEntity<>(adminService.getCustomerById(id),HttpStatus.OK);
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())){
            String newToken = jwt.checkUser(token);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(adminService.getCustomerById(id));
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }

    }

    @PostMapping("/company/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization")String token, @RequestBody Company company) throws LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())){
//            String newToken = jwt.checkUser(token);
            Map<String,Object> newToken = new HashMap<>(); // todo: there is a better solution ?
            newToken.put("Authorization",jwt.checkUser(token));
            adminService.addCompany(company);
            return ResponseEntity.ok(newToken);

        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }


    }

    @PostMapping("/customer/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization")String token,@RequestBody Customer customer) throws LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())) {
//            String newToken = jwt.checkUser(token);
            Map<String,Object> newToken = new HashMap<>(); // todo: there is a better solution ?
            newToken.put("Authorization",jwt.checkUser(token));
            adminService.addCustomer(customer);
            return ResponseEntity.ok(newToken);
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @PutMapping("/company/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token,@RequestBody Company company) throws AdminException, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())){
            Map<String,Object> newToken = new HashMap<>();
            newToken.put("Authorization",jwt.checkUser(token));
            adminService.updateCompany(company);
            return ResponseEntity.ok(newToken);
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }

    }

    @PutMapping("/customer/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token,@RequestBody Customer customer) throws AdminException, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())){
            Map<String,Object> newToken = new HashMap<>();
            newToken.put("Authorization",jwt.checkUser(token));
            adminService.updateCustomer(customer);
            return ResponseEntity.ok(newToken);
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @DeleteMapping("/company/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token,@PathVariable int id) throws AdminException, LoginException {

        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())) {
            Map<String,Object> newToken = new HashMap<>(); // todo: there is a better solution ?
            newToken.put("Authorization",jwt.checkUser(token));
            adminService.deleteCompany(id);
            return ResponseEntity.ok(newToken);
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

    @DeleteMapping("/customer/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token,@PathVariable int id) throws AdminException, LoginException {
        String type = jwt.getClientType(token);
        if (type.equals(ClientType.ADMIN.getName())) {
            Map<String,Object> newToken = new HashMap<>(); // todo: there is a better solution ?
            newToken.put("Authorization",jwt.checkUser(token));
            adminService.deleteCustomer(id);
            return ResponseEntity.ok(newToken);
        }
        else {
            throw new LoginException(ErrorTypes.UNAUTHORIZED_USER.getMessage());
        }
    }

}
