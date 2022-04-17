package com.jb.coupon_system_spring.controller;

import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
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
    public ResponseEntity<?> addCompany(@RequestBody Company company){
        adminService.addCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/customer/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer){
        adminService.addCustomer(customer);
    }

    @PutMapping("/company/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCompany(@RequestBody Company company) throws AdminException {
        adminService.updateCompany(company);
    }

    @PutMapping("/customer/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody Customer customer){
        adminService.updateCustomer(customer);
    }

}
