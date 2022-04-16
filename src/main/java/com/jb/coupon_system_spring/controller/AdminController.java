package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.exceptions.AdminException;
import com.jb.coupon_system_spring.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/allCompanies")
    public ResponseEntity<?> getAllCompanies(){
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
    }
    @GetMapping("/allCustomers")
    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<>(adminService.getAllCustomers(),HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable int id) throws AdminException {
        return new ResponseEntity<>(adminService.getCompanyById(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) throws AdminException {
        return new ResponseEntity<>(adminService.getCustomerById(id),HttpStatus.OK);
    }

    @PostMapping("/company/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company){
        adminService.addCompany(company);

    }

}
