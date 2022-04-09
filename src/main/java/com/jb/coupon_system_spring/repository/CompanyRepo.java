package com.jb.coupon_system_spring.repository;

import com.jb.coupon_system_spring.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company,Integer> {

//    @Query( "SELECT a FROM companies a WHERE email = :email and password = :password")
//    public List<Company> findByEmailAndPassword(
//            @Param("email") String email,
//            @Param("password") String password);
//    Optional<Company> findByEmail(String email);
    public List<Company> findByEmailAndPassword(String email,String password);

}
