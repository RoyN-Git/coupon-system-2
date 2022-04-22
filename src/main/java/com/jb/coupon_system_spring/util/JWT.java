package com.jb.coupon_system_spring.util;

import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.LoginException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWT {
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    //our secret key
    private String secretKey = "this+is+my+key+and+it+must+be+256+bit+long+fnjvnfjvvee";
    //decode the secret key
    private Key decodedSecretKey = new SecretKeySpec
            (Base64.getDecoder().decode(secretKey), this.signatureAlgorithm);
    public static final String CLIENT_TYPE = "client type";
    public static final String ID="id";

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID,0);
        claims.put("name", "Admin");
        claims.put(CLIENT_TYPE, ClientType.ADMIN.getName());
        return "Bearer " + createToken(claims, email);
    }

    public String generateToken(Company company) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, company.getId());
        claims.put("name", company.getName());
        claims.put(CLIENT_TYPE, ClientType.COMPANY.getName());
        return "Bearer " + createToken(claims, company.getEmail());
    }

    public String generateToken(Customer customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, customer.getId());
        claims.put("first name", customer.getFirstName());
        claims.put("last name", customer.getLastName());
        claims.put(CLIENT_TYPE, ClientType.CUSTOMER.getName());
        return "Bearer " + createToken(claims, customer.getEmail());
    }

    public String checkUser(String token) throws MalformedJwtException, LoginException {
        Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        String type = (String) claims.get(CLIENT_TYPE);
        if (type.equals(ClientType.ADMIN.getName())) {
            return generateToken(claims.getSubject());
        } else if (type.equals(ClientType.COMPANY.getName())) {
            Company company = new Company();
            company.setId((int) claims.get(ID));
            company.setName((String) claims.get("name"));
            company.setEmail(claims.getSubject());
            return generateToken(company);
        } else if (type.equals(ClientType.CUSTOMER.getName())) {
            Customer customer = new Customer();
            customer.setId((int) claims.get(ID));
            customer.setFirstName((String) claims.get("first name"));
            customer.setLastName((String) claims.get("last name"));
            customer.setEmail(claims.getSubject());
            return generateToken(customer);
        } else {
            throw new LoginException("unauthorized user");
        }
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException, MalformedJwtException {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token.replace("Bearer ", "")).getBody();
    }

    public String getClientType(String token) {
        Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        return (String) claims.get(CLIENT_TYPE);
    }

    public int getId(String token){
        Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        return (int) claims.get(ID);
    }
    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(decodedSecretKey)
                .compact();
    }
}
